package com.aman.paginationpract

import com.aman.paginationpract.model.Post

class PostRepository {

    suspend fun getAllPosts(): Post {
        return RetrofitInstance.apiService.getAllPost()
    }
}