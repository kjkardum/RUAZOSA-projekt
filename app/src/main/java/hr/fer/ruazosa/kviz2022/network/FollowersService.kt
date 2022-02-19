package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.DTOs.*
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface FollowersService {

    @POST("Followers/AddFollower/{followerId}")
    fun addFollower(@Path(value = "followerId", encoded = true) followerId: Int)


}