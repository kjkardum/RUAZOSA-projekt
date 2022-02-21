package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.dto.DemoApiDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteDemoApiService {
    @GET("https://reqres.in/api/users/{user_id}")
    suspend fun getDemoUser(@Path(value = "user_id", encoded = true) userid: Int): DemoApiDTO
}