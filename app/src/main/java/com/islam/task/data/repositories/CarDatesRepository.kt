package com.islam.task.data.repositories

import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.SafeApiRequest
import com.islam.task.data.network.response.MainResponse
import com.islam.task.generalUtils.Const


class CarDatesRepository(private val api: MyTaskApi) : SafeApiRequest() {

    suspend fun getCarDates(manufacturerId: Int, mainType: String): MainResponse {
        return apiRequest { api.getCarDates(Const.wa_key, manufacturerId, mainType) }
    }

}