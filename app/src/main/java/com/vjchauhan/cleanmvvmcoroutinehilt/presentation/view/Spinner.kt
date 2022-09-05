package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vjchauhan.cleanmvvmcoroutinehilt.R
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.spinner.Data
import com.vjchauhan.cleanmvvmcoroutinehilt.databinding.ActivityMain2Binding
import com.vjchauhan.cleanmvvmcoroutinehilt.databinding.ActivitySpinnerBinding
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.adapter.AutocompleteCustomArrayAdapter
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm.ListViewModelFactory
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm.SampleViewModel
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm.SpinnerViewModel
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm.spinnerViewModelFactory
import javax.inject.Inject

private lateinit var binding: ActivitySpinnerBinding
@Inject
lateinit var factory: spinnerViewModelFactory
lateinit var sampleViewModel:SpinnerViewModel

class Spinner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sampleViewModel = ViewModelProvider(this, factory).get(SpinnerViewModel::class.java)



        binding.SearchableSpinnerCountry.onItemSelectedListener


        val bottomSheet = BottomSheet()
        bottomSheet.show(
            supportFragmentManager,
            "ModelBottomSheet"
        )
    }

    private fun viewlistList() {

        sampleViewModel.getList()
        sampleViewModel.mList.observe(this) { response ->
            when (response) {
                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Success<*> -> {

                   // hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.size}")
                        setCountrySpinner(it[0].data)
                    }
                }
                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Error<*> -> {
                //    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(
                            this@Spinner,
                            "An error occurred : $it",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }

                is com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource.Loading<*> -> {
               //     showProgressBar()
                }

            }
        }



    }

    fun setCountrySpinner(data: List<Data>)
    {
        var myAdapter =
            AutocompleteCustomArrayAdapter(this, R.layout.spinner_item, data)
        binding.SearchableSpinnerCountry .setAdapter(myAdapter)
    }
}