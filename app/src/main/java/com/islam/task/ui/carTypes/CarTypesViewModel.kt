package com.islam.task.ui.carTypes

import androidx.lifecycle.ViewModel
import com.islam.task.data.network.response.MainResponse
import com.islam.task.data.repositories.CarTypesRepository

class CarTypesViewModel(private val carTypesRepository: CarTypesRepository) : ViewModel() {

    suspend fun getMainCarTypes(manufacturerId: Int): MainResponse {
        return carTypesRepository.getMainCarTypes(manufacturerId)
    }

}