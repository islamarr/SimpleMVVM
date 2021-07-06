package com.islam.task.data.network

import com.islam.task.data.network.internet.ConnectivityInterCeptor
import com.islam.task.generalUtils.Utils
import com.google.gson.GsonBuilder
import com.islam.task.data.network.response.ManufacturerResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface MyTaskApi {

    companion object {
        operator fun invoke(
            connectivityInterCeptor: ConnectivityInterCeptor
        ): MyTaskApi {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okkHttpclient = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(connectivityInterCeptor)
                .addInterceptor(interceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(Utils.getUrl())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(MyTaskApi::class.java)
        }
    }

    @GET("manufacturer")
    suspend fun getManufacturer(
        @Query("wa_key") wa_key: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<ManufacturerResponse>

    @GET("main-types")
    suspend fun getMainCarTypes(
        @Query("wa_key") wa_key: String,
        @Query("manufacturer") manufacturer: Int,
    ): Response<ManufacturerResponse>

    @GET("built-dates")
    suspend fun getCarDates(
        @Query("wa_key") wa_key: String,
        @Query("manufacturer") manufacturer: Int,
        @Query("main-type") mainType: String,
    ): Response<ManufacturerResponse>

}

