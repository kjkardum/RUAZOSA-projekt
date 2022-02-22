package hr.fer.ruazosa.kviz2022.network.dto.game

data class GameLeaderboardResponseItemDTO(
    var position: Int?,
    val userId: Int,
    val userName: String,
    val numberOfPoints: Int,
) {
    fun topThree(): Boolean {
        return position!! <= 3
    }
}