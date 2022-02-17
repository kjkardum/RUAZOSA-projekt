package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.forms.*
import hr.fer.ruazosa.kviz2022.forms.RegisterForm
import hr.fer.ruazosa.kviz2022.network.DTOs.UserDTO
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface AccountService {

    @POST("Account/register")
    fun registerNewAccount(@Body registerform: RegisterForm) : Call<ResponseForm>

    @POST("Account/authenticate")
    fun authenticateAccount(@Body loginform: LoginForm) : Call<ResponseForm>

    @POST("Account/forgot-password")
    fun passwordforgotten(@Body passwordforgotten: POST)

    @POST("Account/reset-password")
    fun resetPasswordToken(@Body passwordreset: POST)

    @GET("Account/confirm-email")
    fun confirmMail(): Deferred<List<UserDTO>>

}