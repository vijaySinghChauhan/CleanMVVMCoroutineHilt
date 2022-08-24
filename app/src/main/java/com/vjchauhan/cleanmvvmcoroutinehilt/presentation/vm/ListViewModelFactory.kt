package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases.*


class ListViewModelFactory(
    private val app:Application,
    private val getListUseCase: GetListUseCase,
    private val getSearchedListUseCase: GetSearchedListUseCase,
    private val saveListUseCase: SaveListUseCase,
    private val getSavedListUseCase: GetSavedListUseCase,
    private val deleteSavedListUseCase: DeleteSavedListUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SampleViewModel(
            app,
            getListUseCase,
            getSearchedListUseCase,
            saveListUseCase,
            getSavedListUseCase,
            deleteSavedListUseCase
        ) as T
    }
}









