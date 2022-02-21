package hr.fer.ruazosa.kviz2022.network.dto.authentication

data class ResponseDTO<T>(
    val data: T?,
    val succeeded: Boolean,
    val message: String?,
    val errors: List<String>?
)
