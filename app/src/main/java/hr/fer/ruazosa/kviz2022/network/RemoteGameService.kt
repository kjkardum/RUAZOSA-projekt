package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.dto.game.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RemoteGameService {
    @GET("Game/ActiveGames")
    suspend fun getActiveGames(): List<GameDTO>

    @GET("Game/Leaderboard/{game_id}")
    suspend fun getGameLeaderboard(@Path(value = "game_id", encoded = true) gameId: Int): GameLeaderboardResponseDTO

    @GET("Game/TotalLeaderboard")
    suspend fun getTotalLeaderboard(): List<GameLeaderboardResponseItemDTO>

    @POST("Game/Start")
    suspend fun startNewGame(@Body newGameModel: NewGameDTO): Boolean

    @GET("Game/NextQuestion/{game_id}")
    suspend fun getNextQuestion(@Path(value = "game_id", encoded = true) gameId: Int): QuestionDTO

    @POST("Game/AnswerQuestion/{game_id}")
    suspend fun answerLastQuestion(@Path(value = "game_id", encoded = true) gameId: Int, @Body answer: String): Int
}