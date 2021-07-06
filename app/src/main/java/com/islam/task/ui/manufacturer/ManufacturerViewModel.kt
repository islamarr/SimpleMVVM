package com.islam.task.ui.manufacturer

import androidx.lifecycle.ViewModel
import com.islam.task.data.network.response.ManufacturerResponse
import com.islam.task.data.repositories.ManufacturerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ManufacturerViewModel(private val manufacturerRepository: ManufacturerRepository) : ViewModel() {

    suspend fun getManufacturer() : ManufacturerResponse {
       return manufacturerRepository.getManufacturer()
    }
}