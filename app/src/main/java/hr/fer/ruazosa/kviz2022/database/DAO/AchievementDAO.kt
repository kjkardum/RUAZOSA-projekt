package hr.fer.ruazosa.kviz2022.database.DAO

import androidx.room.*
import hr.fer.ruazosa.kviz2022.database.entity.Achievement

@Dao
interface AchievementDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAchievement(achievement: Achievement)

    @Delete
    fun removeAchievement(achievement: Achievement)

    @Query("SELECT * FROM Achievement")
    fun getAchievement(): Achievement

    @Update
    fun updateAchievement(achievement: Achievement)
}