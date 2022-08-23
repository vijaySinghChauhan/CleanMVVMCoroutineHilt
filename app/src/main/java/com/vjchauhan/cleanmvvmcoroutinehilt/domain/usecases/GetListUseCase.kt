package com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases

import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.ListRepository
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource


class GetListUseCase(private val listRepository: ListRepository) {

    suspend fun execute(): Resource<ApiResponse> {
        return listRepository.getList()
    }
}