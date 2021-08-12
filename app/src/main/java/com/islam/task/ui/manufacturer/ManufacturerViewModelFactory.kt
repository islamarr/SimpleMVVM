package com.islam.task.ui.manufacturer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.islam.task.data.repositories.ManufacturerRepository

class ManufacturerViewModelFactory(
    private val manufacturerRepository: ManufacturerRepository,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ManufacturerViewModel(manufacturerRepository) as T
    }
}