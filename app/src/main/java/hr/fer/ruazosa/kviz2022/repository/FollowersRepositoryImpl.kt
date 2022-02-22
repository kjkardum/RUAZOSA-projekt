package hr.fer.ruazosa.kviz2022.repository

import hr.fer.ruazosa.kviz2022.network.RemoteFollowersService
import hr.fer.ruazosa.kviz2022.network.dto.FollowerDTO
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.FollowersRepository
import javax.inject.Inject

class FollowersRepositoryImpl @Inject constructor(
    private val remoteFollowersService: RemoteFollowersService
) : FollowersRepository {
    override suspend fun getFollowedUsers(): List<GameUserDTO> {
        return remoteFollowersService.getFollowedUsers()
    }

    override suspend fun addFollower(followerId: Int): FollowerDTO {
        return remoteFollowersService.addFollower(followerId)
    }

    override suspend fun followSuggestions(): List<GameUserDTO> {
        return remoteFollowersService.followSuggestions()
    }
}