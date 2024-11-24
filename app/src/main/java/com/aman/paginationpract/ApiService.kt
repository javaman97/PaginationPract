package com.aman.paginationpract

import com.aman.paginationpract.model.PostItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getAllPost() : List<PostItem>
}