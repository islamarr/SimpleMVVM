package com.islam.task.data.network.internet

import android.content.Context
import com.islam.task.R
import com.islam.task.data.network.internet.ConnectivityInterCeptor
import com.islam.task.generalUtils.NoInternetException
import com.islam.task.generalUtils.Utils
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterCeptorImpl(var context: Context) : ConnectivityInterCeptor {
    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Utils.isOnline(appContext))  throw NoInternetException(context.resources.getString(R.string.no_internet_connection))
        return chain.proceed(chain.request())
    }

}