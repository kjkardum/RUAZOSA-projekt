package hr.fer.ruazosa.kviz2022.repository

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import hr.fer.ruazosa.kviz2022.network.RemoteGameService
import hr.fer.ruazosa.kviz2022.network.dto.game.*
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val remoteGameService: RemoteGameService,
    @ApplicationContext var context: Context
) : GameRepository {
    override suspend fun getActiveGames(): List<GameDTO> {
        return remoteGameService.getActiveGames()
    }

    override suspend fun getGameLeaderboard(gameId: Int): GameLeaderboardResponseDTO {
        return remoteGameService.getGameLeaderboard(gameId)
    }

    override suspend fun startNewGame(userIds: List<Int>): Int {
        val gameId = remoteGameService.startNewGame(NewGameDTO(userIds = userIds)).id
        saveGameId(gameId)
        return gameId
    }
    private fun saveGameId(gameId: Int) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
            putInt(LAST_GAME_ID, gameId)
        }.commit()
    }

    override fun getLastStartedGameId(): Int {
        return PreferenceManager
            .getDefaultSharedPreferences(context)
            .getInt(LAST_GAME_ID, 0)
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

    companion object {
        const val LAST_GAME_ID = "LAST_GAME_ID"
    }
}