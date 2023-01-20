package com.monuralan.chatapp.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitAPI {
    @POST("signup/")
    fun  // on below line we are creating a method to post our data.
            postData(@Body dataModel: DataModelRegister?): Call<RegisterResponse?>?
}
