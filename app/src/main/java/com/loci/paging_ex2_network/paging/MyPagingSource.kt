package com.loci.paging_ex2_network.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loci.paging_ex2_network.data.GithubResponseItem
import com.loci.paging_ex2_network.network.GitApi
import kotlinx.coroutines.delay

private const val STARTING_KEY = 1

class MyPagingSource(
    private val githubService: GitApi
) : PagingSource<Int, GithubResponseItem>() {

    init {
        Log.d("MyPagingSource", "init")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubResponseItem> {
        Log.d("MyPagingSource", "load")
        Log.d("MyPagingSource", "params.key: " + params.key)

        val page = params.key ?: STARTING_KEY

        Log.d("MyPagingSource", "page: $page")


        val response = githubService.getData(page, params.loadSize)

        Log.d("MyPagingSource", "response: $response")

        Log.d("MyPagingSource", "response.body: ${response.body()}")

        val data = response.body()

        Log.d("MyPagingSource", "data: $data")


        if (page != 1) {
            delay(3000)
        }

        Log.d("MyPagingSource", "params.loadSize: ${params.loadSize}")
        Log.d("MyPagingSource", "params.loadSize: ${params.loadSize / 30}")


        if (data != null) {
            return LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = page + (params.loadSize / 30)
            )

        } else {
            return LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )

        }


    }


    override fun getRefreshKey(state: PagingState<Int, GithubResponseItem>): Int? {
        return null
    }

}


