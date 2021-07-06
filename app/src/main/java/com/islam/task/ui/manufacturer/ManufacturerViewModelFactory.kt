package com.islam.task.ui.manufacturer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.islam.task.data.repositories.LogoutRepository
import com.islam.task.data.repositories.UserRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ManufacturerViewModelFactory(
    private val userRepository: UserRepository,
    private val logoutRepository: LogoutRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ManufacturerViewModel(userRepository, logoutRepository) as T
    }
}