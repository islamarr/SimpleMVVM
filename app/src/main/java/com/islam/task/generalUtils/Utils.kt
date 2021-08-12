package com.islam.task.generalUtils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.inputmethod.InputMethodManager
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

    fun convertJsonToArray(stringJsonObj: JSONObject): MutableList<ItemModel> {

        val list = mutableListOf<ItemModel>()
        for (key in stringJsonObj.keys()) {
            val value = stringJsonObj.opt(key as String) as String
            list.add(ItemModel(key, value))
        }
        return list
    }


    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun hideKeyboard(context: Activity) {
        val view = context.currentFocus
        view?.let { v ->
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

}