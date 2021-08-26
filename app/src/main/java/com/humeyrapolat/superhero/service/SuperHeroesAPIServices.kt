package com.humeyrapolat.superhero.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Hümeyra POLAT
 * @sınce 23.08.2021
 */


class SuperHeroesAPIServices {

    private val BASE_URL = "https://akabab.github.io/superhero-api/api/"

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(SuperHeroesAPI::class.java)
}