package com.islam.task.data.repositories

import com.islam.task.data.db.AppDatabase
import com.islam.task.data.db.entities.User
import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.SafeApiRequest
import com.islam.task.data.network.response.AuthResponse


class UserRepository(private val api: MyTaskApi, private val db: AppDatabase) : SafeApiRequest() {

    suspend fun userLogin(loginBody: Map<String, String>): AuthResponse {
        return apiRequest { api.userLogin(loginBody) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()

}