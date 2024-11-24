package com.aman.paginationpract

import com.aman.paginationpract.model.PostItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getAllPost() : List<PostItem>

    @GET("posts")
    suspend fun getPost(
    @Query("page") page:Int,
    @Query("pageSize") pageSize:Int
    ):List<PostItem>
}