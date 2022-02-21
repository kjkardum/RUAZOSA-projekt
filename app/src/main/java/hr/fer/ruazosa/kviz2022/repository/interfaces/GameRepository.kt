package hr.fer.ruazosa.kviz2022.repository.interfaces

import hr.fer.ruazosa.kviz2022.network.dto.game.GameDTO
import hr.fer.ruazosa.kviz2022.network.dto.game.GameLeaderboardResponseDTO
import hr.fer.ruazosa.kviz2022.network.dto.game.QuestionDTO

interface GameRepository {
    suspend fun getActiveGames(): List<GameDTO>
    suspend fun getGameLeaderboard(gameId: Int): GameLeaderboardResponseDTO
    suspend fun startNewGame(userIds: List<Int>): Boolean
    suspend fun getNextQuestion(gameId: Int): QuestionDTO
    suspend fun answerLastQuestion(gameId: Int, answer: String): Int
}