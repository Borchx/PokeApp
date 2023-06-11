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
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("sprites") val sprites:PokeImageResponse,
    @SerializedName("types") val types:List<PokeTypesResponse>
)
data class PokeImageResponse(
    @SerializedName("front_default") val url:String
)
data class PokeTypesResponse(
    @SerializedName("slot") val slot:Int,
    @SerializedName("type") val type:PokeTypeResponse
)
data class PokeTypeResponse(
    @SerializedName("name") val nameType:String
)