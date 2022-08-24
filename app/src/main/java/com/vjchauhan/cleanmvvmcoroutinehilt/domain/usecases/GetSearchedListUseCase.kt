package com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases

import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.ListRepository
import com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource

class GetSearchedListUseCase(private val listRepository: ListRepository) {
     suspend fun execute(searchQuery:Int): Resource<ApiResponse> {
         return listRepository.getSearchedList(searchQuery)
     }
}