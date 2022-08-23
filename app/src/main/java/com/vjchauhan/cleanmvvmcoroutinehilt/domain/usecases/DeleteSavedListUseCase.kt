package com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases

import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.ListRepository


class DeleteSavedListUseCase(private val listRepository: ListRepository) {
    suspend fun execute(article: ModelItem)=listRepository.deleteList(article)
}