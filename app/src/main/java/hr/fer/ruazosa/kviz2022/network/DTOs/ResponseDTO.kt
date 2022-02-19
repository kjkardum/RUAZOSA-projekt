package hr.fer.ruazosa.kviz2022.network.DTOs

import com.squareup.moshi.Json

data class ResponseDTO(
    @Json(name = "errors")
    val errors: ErrorDTO?,
    @Json(name = "succeeded")
    val succeeded: Boolean?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "data")
    val data: Int?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "traceId")
    val traceId: String?
)