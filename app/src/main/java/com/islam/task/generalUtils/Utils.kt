package com.islam.task.generalUtils

import android.content.Context
import android.net.ConnectivityManager
import com.islam.task.BuildConfig
import com.islam.task.data.entity.ItemModel
import org.json.JSONObject

object Utils {

    const val DEVURL = "https://api-aws-eu-qa-1.auto1-test.com/v1/car-types/"
    const val URL = "https://api-aws-eu-qa-1.auto1-test.com/v1/car-types/"

    fun getUrl(): String {
        return if (BuildConfig.DEBUG)
            DEVURL
        else
            URL
    }

    fun convertJsonToArray(startingJsonObj: JSONObject): MutableList<ItemModel> {

        val list = mutableListOf<ItemModel>()
        for (key in startingJsonObj.keys()) {
            val value = startingJsonObj.opt(key)
            list.add(ItemModel(key, value as String))
        }
        return list
    }


    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}