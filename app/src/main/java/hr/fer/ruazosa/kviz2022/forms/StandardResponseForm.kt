package hr.fer.ruazosa.kviz2022.forms

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StandardResponseForm (
    val succeeded: Boolean,
    val message: String,
    val errors: String?,
    val data: String
)