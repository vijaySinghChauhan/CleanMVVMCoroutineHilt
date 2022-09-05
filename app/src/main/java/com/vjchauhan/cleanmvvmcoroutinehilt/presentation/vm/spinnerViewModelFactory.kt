package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases.*


class spinnerViewModelFactory(
    private val app:Application,
    private val getListUseCase: GetSpinnerListUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SpinnerViewModel(
            app,
            getListUseCase
        ) as T
    }
}









