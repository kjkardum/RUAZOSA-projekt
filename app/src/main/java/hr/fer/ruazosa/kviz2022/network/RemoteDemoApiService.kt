package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.DTOs.DemoApiDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteDemoApiService {
    @GET("users/{user_id}")
    suspend fun getDemoUser(@Path(value = "user_id", encoded = true) userid: Int): DemoApiDTO
}