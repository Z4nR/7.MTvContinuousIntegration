package com.zulham.mtv.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.zulham.mtv.data.remote.statusresponse.ApiResponse
import com.zulham.mtv.data.remote.statusresponse.StatusResponse
import com.zulham.mtv.utils.AppExecutors
import com.zulham.mtv.vo.Resources

abstract class NetworkResource<ResultType, RequestType> (private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resources<ResultType>>()

    init {
        result.value = Resources.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resources.success(newData)
                }
            }
        }
    }

    private fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resources.loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS ->
                    appExecutors.diskIO().execute {
                        saveCallResult(response.body)
                        appExecutors.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resources.success(newData)
                            }
                        }
                    }
                StatusResponse.EMPTY -> appExecutors.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = Resources.success(newData)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resources.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resources<ResultType>> = result
}