package com.rahul.acro.base

import com.rahul.acro.model.AcroResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("dictionary.py")
    fun acroList(
        @Query("sf") shortForm: String
    ): Single<List<AcroResponse>>

}