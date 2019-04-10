package com.example.daggerandroidcomponentskotlin.api

import com.example.daggerandroidcomponentskotlin.db.MyPojo
import com.example.daggerandroidcomponentskotlin.db.UserDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserWebService {

    @GET("api/users?page=2")
    fun getUsers(): Call<MyPojo>

    @GET("api/users/{id}")
    fun getUser(@Path("id") userID: Int): Call<UserDetail>

}