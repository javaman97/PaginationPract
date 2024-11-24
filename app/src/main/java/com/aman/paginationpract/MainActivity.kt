package com.aman.paginationpract

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aman.paginationpract.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            rcVPost.layoutManager = LinearLayoutManager(this@MainActivity)
            postAdapter = PostAdapter(emptyList())
            rcVPost.adapter = postAdapter
        }

        val postRepository = PostRepository()
        val viewModelFactory = PostViewModelFactory(postRepository)
        postViewModel = ViewModelProvider(this,viewModelFactory)[PostViewModel::class.java]


        postViewModel.posts.observe(this) { postList ->
            postAdapter.updatePostList(postList)
        }

    }
}