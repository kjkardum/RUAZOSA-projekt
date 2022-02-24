package hr.fer.ruazosa.kviz2022.repository

import android.content.Context
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.auth0.android.jwt.JWT
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import hr.fer.ruazosa.kviz2022.network.dto.UserDTO
import hr.fer.ruazosa.kviz2022.network.dto.authentication.AuthenticationResponseDTO
import hr.fer.ruazosa.kviz2022.network.dto.authentication.ResponseDTO
import hr.fer.ruazosa.kviz2022.network.dto.authentication.UserLoginDTO
import hr.fer.ruazosa.kviz2022.network.dto.authentication.UserRegisterDTO
import hr.fer.ruazosa.kviz2022.network.RemoteLoginService
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import java.util.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    var remoteLoginService: RemoteLoginService,
    @ApplicationContext var context: Context,
): UserRepository {
    override suspend fun authenticateAsync(email: String, password: String): ResponseDTO<AuthenticationResponseDTO> {
        logoutUser()
        val user = remoteLoginService.authenticateAccount(
            UserLoginDTO(
            email = email,
            password = password,
        )
        )
        if (!user.succeeded) return user
        val token = user.data!!.jwToken
        PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
            putString(USER_TOKEN_NAME, token)
        }.commit()
        return user
    }

    override suspend fun registerAsync(model: UserRegisterDTO): ResponseDTO<String> {
        logoutUser()
        var response: ResponseDTO<String>
        try{
            response = remoteLoginService.registerNewAccount(model)}
        catch (e: retrofit2.HttpException){
            var responseJSONString = e.response()?.errorBody()?.string()
            data class ResponseDataClass(val type: String, val title: String, val status: Int, val traceId: String, val errors: Map<String, List<String>>)
            val responseJSONObject = Gson().fromJson<ResponseDataClass>(responseJSONString, ResponseDataClass::class.java)
            val errorsList = mutableListOf<String>()
            for(i in responseJSONObject.errors.values){
                errorsList.addAll(i)
            }
            Toast.makeText(context, errorsList.get(0), Toast.LENGTH_SHORT).show()
            response = ResponseDTO<String>("", false, responseJSONObject.title, errorsList)
        }
        return response
    }

    override fun isAuthenticated(): Boolean {
        val user = getUser()
        return user != null
    }

    override fun getUser(): UserDTO? {
        val token = PreferenceManager
            .getDefaultSharedPreferences(context)
            .getString(USER_TOKEN_NAME, "")
        if (token.isNullOrEmpty()) return null
        val jwt = JWT(token)
        val expiresAt = jwt.expiresAt ?: return null
        if (expiresAt < Date()) {
            logoutUser(dontCheckAuthenticated = true);
            return null;
        }
        val userId = jwt.getClaim("uid").asInt() ?: return null
        val email = jwt.getClaim("email").asString() ?: return null
        return UserDTO(
            userId = userId,
            email = email,
            jwToken = token,
        )
    }

    override suspend fun getUserWithUsername(): GameUserDTO {
        return remoteLoginService.getUser()
    }

    override fun logoutUser(dontCheckAuthenticated: Boolean): Boolean {
        if (!dontCheckAuthenticated && !isAuthenticated()) return false
        PreferenceManager
            .getDefaultSharedPreferences(context)
            .edit()
            .remove(USER_TOKEN_NAME)
            .commit()
        return true
    }

    companion object {
        const val USER_TOKEN_NAME = "jw_token"
    }
}