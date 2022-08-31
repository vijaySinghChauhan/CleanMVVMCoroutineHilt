package com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSourceImpl


import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.data.db.ModelItemDAO
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSource.ListLocalDataSource
import kotlinx.coroutines.flow.Flow

class ListLocalDataSourceImpl(
    private val articleDAO: ModelItemDAO
) : ListLocalDataSource {
    override suspend fun saveItemToDB(article: ModelItem) {
        articleDAO.insert(article)
    }

    override fun getSavedItems(): Flow<List<ModelItem>>{
        return articleDAO.getAllList()
    }

    override suspend fun deleteItemsFromDB(article: ModelItem) {
        articleDAO.deleteModel(article)
    }
}