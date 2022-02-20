package hr.fer.ruazosa.kviz2022.network.DTOs.game

data class GameLeaderboardResponseDTO(
    val id: Int,
    val leaderboard: List<GameLeaderboardResponseItemDTO>,
)
