package com.islam.task.ui.carDates

import androidx.lifecycle.ViewModel
import com.islam.task.data.network.response.ManufacturerResponse
import com.islam.task.data.repositories.CarDatesRepository

class CarDatesViewModel(private val carDatesRepository: CarDatesRepository) : ViewModel() {

    suspend fun getCarDates(manufacturerId: Int, mainType: String): ManufacturerResponse {
        return carDatesRepository.getCarDates(manufacturerId, mainType)
    }
}