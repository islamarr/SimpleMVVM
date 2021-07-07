package com.islam.task.data.repositories

import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.SafeApiRequest
import com.islam.task.data.network.response.ManufacturerResponse
import com.islam.task.generalUtils.Const
import com.islam.task.generalUtils.Utils


class CarTypesRepository(private val api: MyTaskApi) : SafeApiRequest() {

    suspend fun getMainCarTypes(manufacturerId: Int): ManufacturerResponse {
        return apiRequest { api.getMainCarTypes(Const.wa_key, manufacturerId) }
    }

}