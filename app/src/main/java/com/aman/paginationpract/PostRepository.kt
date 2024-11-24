package com.aman.paginationpract

import com.aman.paginationpract.model.PostItem

class PostRepository {

    suspend fun getAllPosts(): List<PostItem> {
        return RetrofitInstance.api.getAllPost()
    }

    suspend fun getPostsPage(page:Int, pageSize:Int):List<PostItem>{
        return RetrofitInstance.api.getPost(page,pageSize)
    }
}