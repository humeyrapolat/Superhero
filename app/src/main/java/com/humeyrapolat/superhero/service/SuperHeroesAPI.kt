package com.humeyrapolat.superhero.service

import com.humeyrapolat.superhero.model.Result
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
/**
 * @author Humeyra Polat
 * @sınce 23.08.2021
 */
interface SuperHeroesAPI {

    @GET("id/{resultId}.json")
    fun getId(@Path("resultId") resultId : Long) : Single<Result>


    @GET("all.json")
    fun GetAllInfo(): Single<List<Result>>

}