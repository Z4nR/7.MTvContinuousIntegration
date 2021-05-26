package com.zulham.mtv.core.data.remote

import android.util.Log
import com.zulham.mtv.core.data.remote.network.ApiResponse
import com.zulham.mtv.core.data.remote.network.ApiService
import com.zulham.mtv.core.data.remote.network.apiKey
import com.zulham.mtv.core.data.remote.response.ResultsMovies
import com.zulham.mtv.core.data.remote.response.ResultsTV
import com.zulham.mtv.core.data.remote.response.ShowResponseMovies
import com.zulham.mtv.core.data.remote.response.ShowResponseTV
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){

    fun getMovieList(pageMovie: Int): Flow<ApiResponse<List<ResultsMovies>>> {
        return flow {
            try {
                val response = apiService.getMovieList(apiKey, pageMovie)
                val dataArray = response.results
                if (dataArray != null) {
                    if (dataArray.isNotEmpty()){
                        emit(ApiResponse.Success(response.results))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    fun getTVShowList(pageTV: Int): Flow<ApiResponse<List<ResultsTV>>> {
        return flow {
            try {
                val response = apiService.getTVShowList(apiKey, pageTV)
                val dataArray = response.results
                if (dataArray != null) {
                    if (dataArray.isNotEmpty()){
                        emit(ApiResponse.Success(response.results))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    fun getMovieDetail(idMovie: Int): Flow<ApiResponse<ShowResponseMovies>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(idMovie, apiKey)
                val dataDetail = response.id
                if (dataDetail != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTVDetail(idTV: Int) : Flow<ApiResponse<ShowResponseTV>> {
        return flow {
            try {
                val response = apiService.getTVShowDetail(idTV, apiKey)
                val dataDetail = response.id
                if (dataDetail != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}