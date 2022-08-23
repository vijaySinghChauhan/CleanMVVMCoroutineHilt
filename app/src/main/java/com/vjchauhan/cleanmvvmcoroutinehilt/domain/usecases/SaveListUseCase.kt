package com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases

import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.ListRepository


class SaveListUseCase(private val listRepository: ListRepository) {
  suspend fun execute(article: ModelItem)=listRepository.saveList(article)
}