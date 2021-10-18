package com.rahul.acro.base

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.rahul.acro.model.AcroResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    private val NETWORK_CALL_TIMEOUT = 60
    private val BASE_URL = "http://nactem.ac.uk/software/acromine/"


    val gson = GsonBuilder().create()


    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder()
                .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
        )
        .build()
        .create(Api::class.java)

      fun getAcroList(shortForm: String): Single<List<AcroResponse>> {
        return api.acroList(shortForm)
    }
}