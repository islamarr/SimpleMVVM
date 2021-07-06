package com.islam.task.data.repositories

import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.SafeApiRequest
import com.islam.task.data.network.response.AuthResponse
import com.islam.task.generalUtils.Const


class LogoutRepository(private val api: MyTaskApi) : SafeApiRequest() {

    suspend fun userLogout(): AuthResponse {
        return apiRequest { api.userLogout(Const().authorization) }
    }

}