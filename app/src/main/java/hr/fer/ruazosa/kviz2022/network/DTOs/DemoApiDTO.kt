package hr.fer.ruazosa.kviz2022.network.DTOs

import com.google.gson.annotations.SerializedName


data class DemoApiDTO (
    val data: DemoApiDataDTO = DemoApiDataDTO(),
    val support: DemoApiSupportDTO = DemoApiSupportDTO()

)

data class DemoApiDataDTO (
    val id: Int? = null,
    val email: String? = null,
    @SerializedName("first_name") val firstName: String? = null,
    @SerializedName("last_name") val lastName: String? = null,
    val avatar: String? = null,
)

data class DemoApiSupportDTO(
    val url: String? = null,
    val text: String? = null
)
