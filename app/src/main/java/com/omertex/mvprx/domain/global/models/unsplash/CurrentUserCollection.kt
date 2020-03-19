package com.omertex.test.app.data.model.unsplash

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CurrentUserCollection(
    @Json(name = "cover_photo") val cover_photo: Any?,
    @Json(name = "id") val id: Int,
    @Json(name = "published_at") val published_at: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "updated_at") val updated_at: String?,
    @Json(name = "user") val user: Any?
)