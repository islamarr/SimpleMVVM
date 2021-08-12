package com.islam.task.data.repositories

import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.SafeApiRequest
import com.islam.task.data.network.response.MainResponse
import com.islam.task.generalUtils.Const


class CarTypesRepository(private val api: MyTaskApi) : SafeApiRequest() {

    suspend fun getMainCarTypes(manufacturerId: Int): MainResponse {
        return apiRequest { api.getMainCarTypes(Const.wa_key, manufacturerId) }
    }

}