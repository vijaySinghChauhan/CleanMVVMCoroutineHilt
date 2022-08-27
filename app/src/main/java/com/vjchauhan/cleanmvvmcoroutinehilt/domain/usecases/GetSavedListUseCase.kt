package com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases

import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.ListRepository
import com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource

import kotlinx.coroutines.flow.Flow

class GetSavedListUseCase(private val listRepository: ListRepository) {
    fun execute(): Flow<List<ModelItem>>{
        return listRepository.getSavedList()
    }
}