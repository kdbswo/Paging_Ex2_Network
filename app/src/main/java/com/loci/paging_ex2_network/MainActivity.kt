package com.loci.paging_ex2_network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loci.paging_ex2_network.paging.MyAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

//https://api.github.com/users/google/repos?page=1&per_page=90

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val rv = findViewById<RecyclerView>(R.id.rv)
        val myAdapter = MyAdapter()
        rv.adapter = myAdapter
        rv.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.items.collect{
                myAdapter.submitData(it)
            }
        }

    }
}