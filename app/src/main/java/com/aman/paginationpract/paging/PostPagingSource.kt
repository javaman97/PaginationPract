package com.aman.paginationpract.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aman.paginationpract.model.PostItem
import com.aman.paginationpract.repository.PostRepository

class PostPagingSource(private val postRepository: PostRepository): PagingSource<Int, PostItem>() {

    override fun getRefreshKey(state: PagingState<Int, PostItem>): Int? {

        // Return the key to use for refreshing the data (usually the first item)
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)}

        }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostItem> {
       return try {
           val currentPage = params.key?:1 // Get current page number
           Log.d("PostPagingSource", "Loading page: $currentPage")

           //Fetch data from Repository
            val response = postRepository.getPostsPage(currentPage, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if(currentPage == 1) null else currentPage - 1,
                nextKey = if (response.isEmpty()) null else currentPage + 1
            )

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

}

