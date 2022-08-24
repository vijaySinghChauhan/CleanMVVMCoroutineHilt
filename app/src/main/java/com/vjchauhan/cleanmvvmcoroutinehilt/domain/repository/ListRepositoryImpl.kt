package com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository


import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSourceImpl.ListLocalDataSource
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSourceImpl.ListRemoteDataSource
import com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource


import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ListRepositoryImpl(
    private val listRemoteDataSource: ListRemoteDataSource,
    private val listLocalDataSource: ListLocalDataSource
): ListRepository {
    override suspend fun getList(): Resource<ApiResponse> {
        return responseToResource(listRemoteDataSource.getList())
    }

    override suspend fun getSearchedList(
        searchQuery: String,
    ): Resource<ApiResponse> {
        return responseToResource(
            listRemoteDataSource.getSearchedList(searchQuery)
        )
    }

    private fun responseToResource(response:Response<ApiResponse>):Resource<ApiResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
    private fun responseToResourceSearch(response:Response<ModelItem>):Resource<ModelItem>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }



    override suspend fun saveList(article: ModelItem) {
        listLocalDataSource.saveItemToDB(article)
    }

    override suspend fun deleteList(article: ModelItem) {
        listLocalDataSource.deleteItemsFromDB(article)
    }

    override fun getSavedList(): Flow<List<ModelItem>> {
        return listLocalDataSource.getSavedItems()
    }
}