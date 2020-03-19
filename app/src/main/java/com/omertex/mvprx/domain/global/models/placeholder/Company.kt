package com.omertex.test.app.data.model.placeholder

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Company(
    @Json(name = "bs") val bs: String,
    @Json(name = "catchPhrase") val catchPhrase: String,
    @Json(name = "name") val companyName: String
): Parcelable