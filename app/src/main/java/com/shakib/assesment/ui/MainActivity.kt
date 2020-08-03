package com.shakib.assesment.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shakib.assesment.databinding.ActivityMainBinding
import com.shakib.assesment.model.ApiResponse
import com.shakib.assesment.model.Data

class MainActivity : AppCompatActivity(), MainVMListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter
    private var userList = mutableListOf<Data>()
    private var page = 1
    private var totalPage = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.mainVMListener = this

        viewModel.fetchUsers(page)

        setUpRecyclerView()

        // pagination
        binding.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val totalItem = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val endHasBeenReached = lastVisibleItem + 1 >= totalItem
                if (totalItem > 0 && endHasBeenReached) {
                    page++
                    if (page <= totalPage) {
                        viewModel.fetchUsers(page)
                        binding.progressbar.visibility = View.VISIBLE
                    } else
                        Toast.makeText(this@MainActivity, "Reached The End", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setUpRecyclerView() {
        binding.rvUsers.layoutManager = GridLayoutManager(this, 2)
        adapter = UserAdapter(this, userList)
        binding.rvUsers.adapter = adapter
    }

    override fun onResponseSuccessful(response: ApiResponse) {
        Log.e("GSK", "onResponseSuccessful")
        binding.progressbar.visibility = View.GONE
        totalPage = response.getTotalPages()!!
        adapter.setData(response.getData()!!)
    }

}