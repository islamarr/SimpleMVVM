package com.islam.task.ui.manufacturer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islam.task.R
import com.islam.task.data.db.entities.User
import com.islam.task.data.repositories.LogoutRepository
import com.islam.task.data.repositories.UserRepository
import com.islam.task.generalUtils.ValidationObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ManufacturerViewModel(private val userRepository: UserRepository, private val logoutRepository: LogoutRepository) : ViewModel() {

    suspend fun userLogout() = withContext(Dispatchers.IO) { logoutRepository.userLogout() }

    suspend fun saveLoggedInUser(user: User) = userRepository.saveUser(user)
    fun getLoggedInUser() = userRepository.getUser()

}