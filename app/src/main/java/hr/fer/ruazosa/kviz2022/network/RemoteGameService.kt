package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.DTOs.game.GameDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.game.GameLeaderboardResponseDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteGameService {
    @GET("Game/ActiveGames")
    fun getActiveGames(): Deferred<List<GameDTO>>

    @GET("Game/Leaderboard/{game_id}")
    fun getGameLeaderboard(@Path(value = "game_id", encoded = true) gameId: Int): Deferred<GameLeaderboardResponseDTO>
}