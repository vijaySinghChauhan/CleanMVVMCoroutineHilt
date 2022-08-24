package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases.*
import com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SampleViewModel(
    private val app:Application,
    private val getListUseCase: GetListUseCase,
    private val getSearchedListUseCase: GetSearchedListUseCase,
    private val saveListUseCase: SaveListUseCase,
    private val getSavedListUseCase: GetSavedListUseCase,
    private val deleteSavedListUseCase: DeleteSavedListUseCase
) : AndroidViewModel(app) {
    val mList: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun getList() = viewModelScope.launch(Dispatchers.IO) {
        mList.postValue(Resource.Loading())
        try{
      if(isNetworkAvailable(app)) {

          val apiResult = getListUseCase.execute()
          mList.postValue(apiResult)
      }else{
          mList.postValue(Resource.Error("Internet is not available"))
      }

        }catch (e:Exception){
            mList.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    //search
    val searchedList :  MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun searchlist(

        searchQuery : String,

    ) = viewModelScope.launch {

        searchedList.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getSearchedListUseCase.execute(
                    searchQuery
                )

                searchedList.postValue(response)
            } else {
                searchedList.postValue(Resource.Error("No internet connection"))
            }
        }catch(e:Exception){
            searchedList.postValue(Resource.Error(e.message.toString()))
        }
    }

    //local data
    fun saveArticle(article: ModelItem) = viewModelScope.launch {
        saveListUseCase.execute(article)
    }

    fun getSavedlist() = liveData{
        getSavedListUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteArticle(article: ModelItem) = viewModelScope.launch {
        deleteSavedListUseCase.execute(article)
    }

}














