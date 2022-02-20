package hr.fer.ruazosa.kviz2022.network.DTOs

data class GameLeaderboardResponseItemDTO(
    val userId: Int,
    val userName: String,
    val numberOfPoints: Int,
)
