package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.DTOs.game.GameDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.game.GameLeaderboardResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.game.NewGameDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.game.QuestionDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RemoteGameService {
    @GET("Game/ActiveGames")
    suspend fun getActiveGames(): List<GameDTO>

    @GET("Game/Leaderboard/{game_id}")
    suspend fun getGameLeaderboard(@Path(value = "game_id", encoded = true) gameId: Int): GameLeaderboardResponseDTO

    @POST("Game/Start")
    suspend fun startNewGame(@Body newGameModel: NewGameDTO): GameDTO

    @GET("Game/NextQuestion/{game_id}")
    suspend fun getNextQuestion(@Path(value = "game_id", encoded = true) gameId: Int): QuestionDTO

    @POST("Game/AnswerQuestion/{game_id}")
    suspend fun answerLastQuestion(@Path(value = "game_id", encoded = true) gameId: Int, @Body answer: String): Int
}