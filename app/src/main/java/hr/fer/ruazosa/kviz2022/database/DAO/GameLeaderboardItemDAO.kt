package hr.fer.ruazosa.kviz2022.database.DAO

import androidx.room.*
import hr.fer.ruazosa.kviz2022.database.entity.GameLeaderboardItem
import kotlinx.coroutines.flow.Flow

@Dao
interface GameLeaderboardItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(gameLeaderboardItem: GameLeaderboardItem)

    @Delete
    fun removeItem(gameLeaderboardItem: GameLeaderboardItem)

    @Query("SELECT * FROM GameLeaderboardItem ORDER BY NumberOfPoints DESC")
    fun getItem(): Flow<List<GameLeaderboardItem>>

    @Update
    fun updateItem(gameLeaderboardItem: GameLeaderboardItem)
}