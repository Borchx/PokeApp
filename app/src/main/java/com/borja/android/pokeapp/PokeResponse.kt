package com.borja.android.pokeapp

import com.google.gson.annotations.SerializedName

//2 Data Class
/*data class PokeResponse(
    @SerializedName("results") val pokemon:List<PokeItemResponse>,
)*/
data class PokeResponse(
    @SerializedName("results") val pokemon:List<PokeItemResponse>
)
data class PokeItemResponse(
    @SerializedName("name") val name:String,
    @SerializedName("sprites") val sprites:PokeImageResponse
)
data class PokeImageResponse(
    @SerializedName("front_default") val url:String

)