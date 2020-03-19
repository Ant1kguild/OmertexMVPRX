package com.omertex.test.app.data.model.placeholder

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Geo(
    @Json(name = "lat") val lat: String,
    @Json(name = "lng") val lng: String
): Parcelable