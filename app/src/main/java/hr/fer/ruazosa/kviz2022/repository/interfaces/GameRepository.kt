package hr.fer.ruazosa.kviz2022.repository.interfaces

import hr.fer.ruazosa.kviz2022.network.DTOs.game.GameDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.game.GameLeaderboardResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.game.QuestionDTO

interface GameRepository {
    suspend fun getActiveGames(): List<GameDTO>
    suspend fun getGameLeaderboard(gameId: Int): GameLeaderboardResponseDTO
    suspend fun startNewGame(userIds: List<Int>): Boolean
    suspend fun getNextQuestion(gameId: Int): QuestionDTO
    suspend fun answerLastQuestion(gameId: Int, answer: String): Int
}