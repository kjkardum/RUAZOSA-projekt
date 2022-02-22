package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.dto.FollowerDTO
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import retrofit2.http.*

interface RemoteFollowersService {
    @GET("Followers/GetFollowedUsers")
    suspend fun getFollowedUsers(): List<GameUserDTO>

    @POST("Followers/AddFollower/{followerId}")
    suspend fun addFollower(@Path(value = "followerId", encoded = true) followerId: Int): FollowerDTO

    @GET("Followers/FollowSuggestions")
    suspend fun followSuggestions(): List<GameUserDTO>
}