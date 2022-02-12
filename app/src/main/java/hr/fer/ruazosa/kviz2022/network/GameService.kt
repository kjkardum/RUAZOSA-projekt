package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.DTOs.GameDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.GameLeaderboardResponseDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GameService {
    @GET("Game/ActiveGames")
    fun getActiveGames(): Deferred<List<GameDTO>>

    @GET("Game/Leaderboard/{game_id}")
    fun getGameLeaderboard(@Path(value = "game_id", encoded = true) gameId: Int): Deferred<GameLeaderboardResponseDTO>
}