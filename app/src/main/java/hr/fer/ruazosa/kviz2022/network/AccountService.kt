package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.DTOs.*
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface AccountService {

    @POST("Account/register")
    fun registerNewAccount(@Body registerform: RegisterDTO) : Call<ResponseDTO>

    @POST("Account/authenticate")
    fun authenticateAccount(@Body loginform: LoginDTO) : Call<ResponseDTO>

    @POST("Account/forgot-password")
    fun passwordforgotten(@Body passwordforgotten: POST)

    @POST("Account/reset-password")
    fun resetPasswordToken(@Body passwordreset: POST)

    @GET("Account/confirm-email")
    fun confirmMail(): Deferred<List<UserDTO>>

}