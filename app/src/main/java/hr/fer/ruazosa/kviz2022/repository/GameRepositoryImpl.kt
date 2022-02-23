package hr.fer.ruazosa.kviz2022.repository

import hr.fer.ruazosa.kviz2022.network.RemoteGameService
import hr.fer.ruazosa.kviz2022.network.dto.game.*
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val remoteGameService: RemoteGameService
) : GameRepository {
    override suspend fun getActiveGames(): List<GameDTO> {
        return remoteGameService.getActiveGames()
    }

    override suspend fun getGameLeaderboard(gameId: Int): GameLeaderboardResponseDTO {
        return remoteGameService.getGameLeaderboard(gameId)
    }

    override suspend fun startNewGame(userIds: List<Int>): Int {
        return remoteGameService.startNewGame(NewGameDTO(userIds = userIds)).id
    }

    override suspend fun getNextQuestion(gameId: Int): QuestionDTO {
        return remoteGameService.getNextQuestion(gameId)
    }

    override suspend fun answerLastQuestion(gameId: Int, answer: String): Int {
        return remoteGameService.answerLastQuestion(gameId, answer)
    }

    override suspend fun getTotalLeaderboard(): List<GameLeaderboardResponseItemDTO> {
        var items = remoteGameService.getTotalLeaderboard()
        items.forEachIndexed { index, element ->
            element.position = index + 1
        }
        return items;
    }
}