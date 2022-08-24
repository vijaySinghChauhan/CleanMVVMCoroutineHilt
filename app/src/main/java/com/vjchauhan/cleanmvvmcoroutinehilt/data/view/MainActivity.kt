package com.vjchauhan.cleanmvvmcoroutinehilt.data.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vjchauhan.cleanmvvmcoroutinehilt.databinding.ActivityMainBinding
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.adapter.SampleListAdapter
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm.ListViewModelFactory
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm.ViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ListViewModelFactory

    @Inject
    lateinit var sampleListAdapter: SampleListAdapter
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    lateinit var viewModel: ViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        binding.bnv.setupWithNavController(
//            navController
//        )

        viewModel = ViewModelProvider(this, factory)
            .get(ViewModel::class.java)

        initRecyclerView()
        viewlistList()
        setSearchView()
    }

    private fun viewlistList() {

        viewModel.getList()
        viewModel.mList.observe(this, { response ->
            when (response) {
                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Success<*> -> {

                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.size}")
                        sampleListAdapter.differ.submitList(it.toList())
                        if (it .size % 20 == 0) {
                            pages = it.size / 20
                        } else {
                            pages = it.size / 20 + 1
                        }
                        isLastPage = page == pages
                    }
                }
                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Error<*> -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(this@MainActivity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Loading<*> -> {
                    showProgressBar()
                }

            }
        })
    }

    private fun initRecyclerView() {
        // listAdapter = listAdapter()
        binding.recyclerView.apply {
            adapter = sampleListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnScrollListener(this@MainActivity.onScrollListener)
        }

    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, listtate: Int) {
            super.onScrollStateChanged(recyclerView, listtate)
            if (listtate == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager =
                binding.recyclerView.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                page++
                viewModel.getList()
                isScrolling = false

            }


        }
    }

    //search
    private fun setSearchView() {
        binding.svList.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    viewModel.searchlist( p0.toString())
                //    viewSearchedlist()
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    MainScope().launch {
                        delay(2000)
                        viewModel.searchlist( p0.toString())
                      //  viewSearchedlist()
                    }
                    return false
                }

            })

        binding.svList.setOnCloseListener(
            object : SearchView.OnCloseListener {
                override fun onClose(): Boolean {
                    initRecyclerView()
                    viewlistList()
                    return false
                }

            })
    }
}




    /*fun viewSearchedlist(){
        viewModel.searchedlist.observe(viewLifecycleOwner,{response->
            when(response){
                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Success->{

                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG","came here ${it.articles.toList().size}")
                        listAdapter.differ.submitList(it.articles.toList())
                        if(it.totalResults%20 == 0) {
                            pages = it.totalResults / 20
                        }else{
                            pages = it.totalResults/20+1
                        }
                        isLastPage = page == pages
                    }
                }
                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Error->{
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(this@MainActivity,"An error occurred : $it", Toast.LENGTH_LONG).show()
                    }
                }

                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Loading->{
                    showProgressBar()
                }

            }
        })
    }*/