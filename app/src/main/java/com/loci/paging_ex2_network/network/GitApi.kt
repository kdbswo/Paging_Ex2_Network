package com.loci.paging_ex2_network.network

import com.loci.paging_ex2_network.data.GithubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApi {

    // https://api.github.com/users/google/repos?page=1&per_page=90
    // https://api.github.com/users/google/repos?page=4&per_page=30

    @GET("users/google/repos")
    suspend fun getData(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<GithubResponse>


}