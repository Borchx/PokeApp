package com.borja.android.pokeapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    //3 APISERVICE GET
    @GET
    suspend fun getPokemon(@Url url:String): Response<PokeItemResponse>
}