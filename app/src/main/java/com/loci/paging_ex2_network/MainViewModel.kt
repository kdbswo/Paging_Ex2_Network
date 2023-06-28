package com.loci.paging_ex2_network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.loci.paging_ex2_network.data.GithubResponseItem
import com.loci.paging_ex2_network.network.GitApi
import com.loci.paging_ex2_network.network.RetrofitInstance
import com.loci.paging_ex2_network.paging.MyPagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    private val api = RetrofitInstance.getInstance().create(GitApi::class.java)

    val items : Flow<PagingData<GithubResponseItem>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            MyPagingSource(api)
        }
    )
        .flow
        .cachedIn(viewModelScope)
}