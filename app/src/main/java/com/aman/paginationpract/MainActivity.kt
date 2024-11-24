package com.aman.paginationpract

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aman.paginationpract.databinding.ActivityMainBinding
import com.aman.paginationpract.model.Post
import com.aman.paginationpract.model.PostItem
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val postList = listOf(
            PostItem("Item 1",1,"One",1),
            PostItem("Item 1",1,"One",1),
            PostItem("Item 1",1,"One",1),PostItem("Item 1",1,"One",1),
            PostItem("Item 1",1,"One",1),
            PostItem("Item 1",1,"One",1),

        )

        binding.apply {
            rcVPost.layoutManager = LinearLayoutManager(this@MainActivity)
            rcVPost.adapter = PostAdapter(postList)
        }



    }
}