package com.islam.task.ui.carTypes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.islam.task.data.repositories.CarTypesRepository
import com.islam.task.data.repositories.ManufacturerRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class CarTypesViewModelFactory(
    private val carTypesRepository: CarTypesRepository,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CarTypesViewModel(carTypesRepository) as T
    }
}