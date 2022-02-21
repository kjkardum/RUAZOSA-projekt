package hr.fer.ruazosa.kviz2022.network.dto


data class GameUserDto(
    val id: Int,
    val userName: String?,
    val totalGamesPoints: Int,
    val following: List<FollowerDTO>?
)
