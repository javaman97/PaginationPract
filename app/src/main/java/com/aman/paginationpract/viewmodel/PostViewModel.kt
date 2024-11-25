package com.aman.paginationpract.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aman.paginationpract.model.PostItem
import com.aman.paginationpract.paging.PostPagingSource
import com.aman.paginationpract.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class PostViewModel(private val postRepository: PostRepository):ViewModel() {

    private val _posts = MutableLiveData<List<PostItem>>()
    val posts:LiveData<List<PostItem>> get() = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch{
            try {
                val postList = postRepository.getAllPosts()
                _posts.value = postList
            } catch (e:Exception){
                Log.e("Fetch Posts", "${e.message}")
            }
        }
    }

    val pagingPosts : Flow<PagingData<PostItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,   // Number of items per page
            enablePlaceholders = false  // disable placeholders
        ),
        pagingSourceFactory = {
            PostPagingSource(postRepository)
        }
    ).flow.cachedIn(viewModelScope)
}