package com.dani.punkbeerapp.data.model

import com.google.gson.annotations.SerializedName

data class Beer(
    @SerializedName("id")
    val id: Int,
    @SerializedName("abv")
    val abv: Double,
    @SerializedName("brewers_tips")
    val brewersTips: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("tagline")
    val tagline: String
)