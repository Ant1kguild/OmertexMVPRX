package com.omertex.test.app.data.model.placeholder

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "city") val city: String,
    @Json(name = "geo") val geo: Geo,
    @Json(name = "street") val street: String,
    @Json(name = "suite") val suite: String,
    @Json(name = "zipcode") val zipCode: String
): Parcelable