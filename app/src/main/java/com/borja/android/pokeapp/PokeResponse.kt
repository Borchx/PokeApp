package com.borja.android.pokeapp

import com.google.gson.annotations.SerializedName

//2 Data Class
data class PokeResponse(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("height") val height:Int,
    @SerializedName("weight") val weight:Int,
    @SerializedName("sprites") val pokeImages:PokeImageResponse
)

data class PokeImageResponse(
    @SerializedName("front_default") val image:String
)
