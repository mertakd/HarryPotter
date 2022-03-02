package com.androiddev.internetharrypotter.network

import com.squareup.moshi.Json

data class CharacterResponse (
    //veriler response a geliyor web servisten
    @Json(name = "harrypotter") var characters: List<CharacterModel>,
    var success: Int
        )