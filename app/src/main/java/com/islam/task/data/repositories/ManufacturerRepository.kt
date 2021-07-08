package com.islam.task.data.repositories

import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.SafeApiRequest
import com.islam.task.data.network.response.ManufacturerResponse
import com.islam.task.generalUtils.Const
import com.islam.task.generalUtils.Utils


class ManufacturerRepository(private val api: MyTaskApi) : SafeApiRequest() {

    suspend fun getManufacturer(page: Int, pageSize: Int): ManufacturerResponse {
        return apiRequest { api.getManufacturer(Const.wa_key,page, pageSize) }
    }

}