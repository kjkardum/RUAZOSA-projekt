package hr.fer.ruazosa.kviz2022.network.DTOs

data class GameLeaderboardResponseDTO(
    val id: Int,
    val leaderboard: List<GameLeaderboardResponseItemDTO>,
)
