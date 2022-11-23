package com.example.pixabay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {


    @GET("/api/")
    fun getImage(
                @Query("q") keyWord:String,
                @Query("key")key:String = "31551321-e6dd76f6294f35385ee87bb13",
                @Query("page") page:Int,
                @Query("per_page") perPage:Int
    ) : Call<PixaModel>
}