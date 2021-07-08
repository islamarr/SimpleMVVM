package com.islam.task.ui.manufacturer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.islam.task.data.network.response.ManufacturerResponse
import com.islam.task.data.repositories.ManufacturerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ManufacturerViewModel(private val manufacturerRepository: ManufacturerRepository) : ViewModel() {

    /*private val _queryLiveData = MutableLiveData<List<TripItem>>()
    var queryLiveData: LiveData<List<TripItem>> = _queryLiveData

    init {
        DataSource.tripRequestBody.skipCount = 0
        DataSource.tripRequestBody.maxResultCount = 10

        repository.queryLiveData.observeForever {
            _queryLiveData.postValue(it)
        }
    }*/

    var manufacturerList =
        Pager(
            config = PagingConfig(
                pageSize = 15,
                enablePlaceholders = false,
                initialLoadSize = 15
            ),
            pagingSourceFactory = {
                DataSource(manufacturerRepository)
            }
        ).flow

    suspend fun getManufacturer() : ManufacturerResponse {
       return manufacturerRepository.getManufacturer(0, 15)
    }
}