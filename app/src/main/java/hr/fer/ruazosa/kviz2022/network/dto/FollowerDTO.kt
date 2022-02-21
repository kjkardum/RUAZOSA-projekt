package hr.fer.ruazosa.kviz2022.network.dto


data class FollowerDTO(
    val id: Int,
    val followerId: Int,
    val follower: GameUserDto?,
    val followedId: Int,
    val followed: GameUserDto?
)
