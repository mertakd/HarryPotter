package com.androiddev.internetharrypotter.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(
    val id: Int,
    val name: String,
    @Json(name = "image_url") val imageUrl: String,
    val house: String

    /*Parcelize Srelizable dan 10 kat daha hızlı
    *Serilazable verileri stringe çevirip sonra de serilazable yapıp eski haline döndürüyordu
    * Parcable ise stringden farklı olarak byte array e dönüştürüyor. bu da verilerin geçiş hızını 10 kat arttıran bir sistem
     */
): Parcelable
