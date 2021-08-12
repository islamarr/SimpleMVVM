package com.islam.task.ui.carDates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.islam.task.data.repositories.CarDatesRepository

class CarDatesViewModelFactory(
    private val carDatesRepository: CarDatesRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CarDatesViewModel(carDatesRepository) as T
    }
}