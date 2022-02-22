package hr.fer.ruazosa.kviz2022.repository.interfaces

import hr.fer.ruazosa.kviz2022.network.dto.FollowerDTO
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import retrofit2.http.Path

interface FollowersRepository {
    suspend fun getFollowedUsers(): List<GameUserDTO>
    suspend fun addFollower(followerId: Int): FollowerDTO
    suspend fun followSuggestions(): List<GameUserDTO>
}