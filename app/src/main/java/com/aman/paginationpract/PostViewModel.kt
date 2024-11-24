package com.aman.paginationpract

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.paginationpract.model.Post
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository):ViewModel() {

    private val _posts = MutableLiveData<Post>()
    private val posts:LiveData<Post> get() = _posts

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
}