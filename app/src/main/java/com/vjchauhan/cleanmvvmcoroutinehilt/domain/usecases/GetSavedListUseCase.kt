package com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases

import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.ListRepository

import kotlinx.coroutines.flow.Flow

class GetSavedListUseCase(private val listRepository: ListRepository) {
    fun execute(): Flow<List<ModelItem>>{
        return listRepository.getSavedList()
    }
}