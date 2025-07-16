package com.hasangurgur.rickandmortycompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results")
    val results: List<CharacterDto>
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val image: String,
    val species : String,
    val gender : String,
    val location : LocationOriginDto,
    val origin : LocationOriginDto
)

data class LocationOriginDto(
    val name: String,
    val url: String
)