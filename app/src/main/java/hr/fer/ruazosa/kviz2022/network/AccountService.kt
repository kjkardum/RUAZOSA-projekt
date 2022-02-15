package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.account.forms.LoginForm
import hr.fer.ruazosa.kviz2022.account.forms.LoginResponseForm
import hr.fer.ruazosa.kviz2022.account.forms.RegisterForm
import hr.fer.ruazosa.kviz2022.account.forms.RegisterResponseForm
import hr.fer.ruazosa.kviz2022.network.DTOs.UserDTO
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.io.Serializable;

interface AccountService {

    @POST("Account/register")
    fun registerNewAccount(@Body registerform: RegisterForm) : Call<RegisterResponseForm>

    @POST("Account/authenticate")
    fun authenticateAccount(@Body loginform: LoginForm) : Call<LoginResponseForm>

    @POST("Account/forgot-password")
    fun passwordforgotten(@Body passwordforgotten: POST)

    @POST("Account/reset-password")
    fun resetPasswordToken(@Body passwordreset: POST)

    @GET("Account/confirm-email")
    fun confirmMail(): Deferred<List<UserDTO>>

}