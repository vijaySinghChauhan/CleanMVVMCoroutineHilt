package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.view


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.EditText

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vjchauhan.cleanmvvmcoroutinehilt.databinding.ActivityMain2Binding
import com.vjchauhan.cleanmvvmcoroutinehilt.databinding.ActivityMainBinding
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.adapter.SampleListAdapter
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm.ListViewModelFactory
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm.SampleViewModel
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

    lateinit var sampleViewModel: SampleViewModel
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        sampleViewModel = ViewModelProvider(this, factory)
            .get(SampleViewModel::class.java)

        initRecyclerView()
        viewlistList()
        setSearchView()
    }

    private fun viewlistList() {

        sampleViewModel.getList()
        sampleViewModel.mList.observe(this) { response ->
            when (response) {
                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Success<*> -> {

                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.size}")
                        sampleListAdapter.differ.submitList(it.toList())
                        if (it.size % 20 == 0) {
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
                        Toast.makeText(
                            this@MainActivity,
                            "An error occurred : $it",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }

                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Loading<*> -> {
                    showProgressBar()
                }

            }
        }
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
                sampleViewModel.getList()
                isScrolling = false

            }
        }
    }

    //search
    private fun setSearchView() {

        binding.sv?.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    try {
                        if (p0 != null) {
                            sampleViewModel.searchlist( p0.toInt())
                        }
                    }
                    catch (e:Exception)
                    {
                        sampleViewModel.getList()
                    }

                    viewSearchedlist()
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    MainScope().launch {
                        delay(1000)


                        try {
                            if (p0 != null) {
                                sampleViewModel.searchlist( p0.toInt())
                            }
                        }
                        catch (e:Exception)
                        {
                            sampleViewModel.getList()
                        }
                        viewSearchedlist()
                    }
                    return false
                }

            })

        binding.sv?.setOnCloseListener {
            initRecyclerView()
            viewlistList()
            false
        }
    }
    fun viewSearchedlist(){
        sampleViewModel.searchedList.observe(this) { response ->
            when (response) {
                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Success<*> -> {

                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG", "search here ${it.toList()}")
                        sampleListAdapter.differ.submitList(it.toList())
                        if (it.size % 20 == 0) {
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
                        Toast.makeText(
                            this@MainActivity,
                            "An error occurred : $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Loading<*> -> {
                    showProgressBar()
                }
            }
        }
    }
}




