package hr.fer.ruazosa.kviz2022.repository

import hr.fer.ruazosa.kviz2022.network.dto.game.GameDTO
import hr.fer.ruazosa.kviz2022.network.dto.game.GameLeaderboardResponseDTO
import hr.fer.ruazosa.kviz2022.network.dto.game.QuestionDTO
import hr.fer.ruazosa.kviz2022.network.RemoteGameService
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val remoteGameService: RemoteGameService
) : GameRepository {
    override suspend fun getActiveGames(): List<GameDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getGameLeaderboard(gameId: Int): GameLeaderboardResponseDTO {
        TODO("Not yet implemented")
    }

    override suspend fun startNewGame(userIds: List<Int>): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getNextQuestion(gameId: Int): QuestionDTO {
        TODO("Not yet implemented")
    }

    override suspend fun answerLastQuestion(gameId: Int, answer: String): Int {
        TODO("Not yet implemented")
    }
}