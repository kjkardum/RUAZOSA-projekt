package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.forms.StandardResponseForm
import hr.fer.ruazosa.kviz2022.forms.*
import hr.fer.ruazosa.kviz2022.network.DTOs.GameDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.GameLeaderboardResponseDTO
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GameService {
    @GET("Game/ActiveGames")
    fun getActiveGames(): Deferred<List<GameDTO>>

    @GET("Game/Leaderboard/{game_id}")
    fun getGameLeaderboard(@Path(value = "game_id", encoded = true) gameId: Int): Deferred<GameLeaderboardResponseDTO>

    @POST("Game/Start")
    fun startGame(@Body start: GameStartForm): Call<StandardResponseForm>

    @GET("Game/NextQuestion/{gameId}")
    fun getNextQuestion(@Path(value = "game_id", encoded = true) gameId: Int)
}