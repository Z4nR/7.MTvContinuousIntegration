package com.zulham.mtv.core.utils

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {

    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun idlingIncrement(){
        idlingResource.increment()
    }

    fun idlingDecrement(){
        idlingResource.decrement()
    }

}