package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.DTOs.UserDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET

interface RemoteLoginService {
    @POST("Account/register")
    fun registerNewAccount(@Body register: POST)

    @POST("Account/authenticate")
    fun authenticateAccount(@Body login: POST)

    @POST("Account/forgot-password")
    fun passwordforgotten(@Body passwordforgotten: POST)

    @POST("Account/reset-password")
    fun resetPasswordToken(@Body passwordreset: POST)

    @GET("Account/confirm-email")
    fun confirmMail(): Deferred<List<UserDTO>>

}