package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.model.User
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Response<List<User>>
}