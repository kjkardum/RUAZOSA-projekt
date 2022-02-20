package hr.fer.ruazosa.kviz2022.repository

import android.content.Context
import androidx.preference.PreferenceManager
import com.auth0.android.jwt.JWT
import dagger.hilt.android.qualifiers.ApplicationContext
import hr.fer.ruazosa.kviz2022.network.DTOs.UserDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.AuthenticationResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.ResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.UserLoginDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.UserRegisterDTO
import hr.fer.ruazosa.kviz2022.network.RemoteLoginService
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import java.util.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    var remoteLoginService: RemoteLoginService,
    @ApplicationContext var context: Context,
): UserRepository {
    override suspend fun authenticateAsync(email: String, password: String): ResponseDTO<AuthenticationResponseDTO> {
        logoutUser()
        val user = remoteLoginService.authenticateAccount(UserLoginDTO(
            email = email,
            password = password,
        ))
        if (!user.succeeded) return user
        val token = user.data!!.jwToken
        PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
            putString(USER_TOKEN_NAME, token)
        }.commit()
        return user
    }

    override suspend fun registerAsync(model: UserRegisterDTO): ResponseDTO<String> {
        logoutUser()
        return remoteLoginService.registerNewAccount(model)
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
        if (expiresAt < Date()) return null
        val userId = jwt.getClaim("uid").asInt() ?: return null
        val email = jwt.getClaim("email").asString() ?: return null
        return UserDTO(
            userId = userId,
            email = email,
            jwToken = token,
        )
    }

    override fun logoutUser(): Boolean {
        if (!isAuthenticated()) return false
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