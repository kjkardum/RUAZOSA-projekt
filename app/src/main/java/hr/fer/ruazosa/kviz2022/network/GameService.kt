package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.DTOs.*
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
    fun startGame(@Body start: GameStartDTO): Deferred<ResponseDTO>

    @GET("Game/NextQuestion/{gameId}")
    fun getNextQuestion(@Path(value = "gameId", encoded = true) gameId: Int): Deferred<ResponseDTO>

    @POST("Game/AnswerQuestion/{gameId}")
    fun answerQuestion(@Path(value = "gameId", encoded = true) gameId: Int): Call<QuestionDTO>
}