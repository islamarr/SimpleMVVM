package com.islam.task.data.repositories

import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.SafeApiRequest
import com.islam.task.data.network.response.ManufacturerResponse
import com.islam.task.generalUtils.Const
import com.islam.task.generalUtils.Utils


class CarDatesRepository(private val api: MyTaskApi) : SafeApiRequest() {

    suspend fun getCarDates(manufacturerId: Int, mainType: String): ManufacturerResponse {
        return apiRequest { api.getCarDates(Const.wa_key, manufacturerId, mainType) }
    }

}