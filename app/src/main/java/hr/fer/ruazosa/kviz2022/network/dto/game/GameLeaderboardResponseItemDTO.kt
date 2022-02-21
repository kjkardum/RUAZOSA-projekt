package hr.fer.ruazosa.kviz2022.network.dto.game

data class GameLeaderboardResponseItemDTO(
    val userId: Int,
    val userName: String,
    val numberOfPoints: Int,
)
