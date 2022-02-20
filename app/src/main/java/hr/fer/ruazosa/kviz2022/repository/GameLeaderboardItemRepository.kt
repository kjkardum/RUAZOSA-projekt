package hr.fer.ruazosa.kviz2022.repository

import hr.fer.ruazosa.kviz2022.database.entity.GameLeaderboardItem
import hr.fer.ruazosa.kviz2022.database.DAO.GameLeaderboardItemDAO
import hr.fer.ruazosa.kviz2022.database.entity.Achievement
import kotlinx.coroutines.flow.Flow

class GameLeaderboardItemRepository(private val gameLeaderboardItemDAO: GameLeaderboardItemDAO) {
    val getGameLeaderboard: Flow<List<GameLeaderboardItem>> = gameLeaderboardItemDAO.getItem()

    fun addItem(item: GameLeaderboardItem){
        gameLeaderboardItemDAO.addItem(item)
    }

    fun updateAchievement(item: GameLeaderboardItem){
        gameLeaderboardItemDAO.updateItem(item)
    }

    fun removeAchievement(item: GameLeaderboardItem){
        gameLeaderboardItemDAO.removeItem(item)
    }
}