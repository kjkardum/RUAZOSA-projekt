package hr.fer.ruazosa.kviz2022.repository

import hr.fer.ruazosa.kviz2022.database.DAO.AchievementDAO
import hr.fer.ruazosa.kviz2022.database.entity.Achievement

class AchievementRepository(private val achievementDAO: AchievementDAO) {
    val getAchievement: Achievement = achievementDAO.getAchievement()

    fun addAchievement(achievement: Achievement){
        achievementDAO.addAchievement(achievement)
    }

    fun updateAchievement(achievement: Achievement){
        achievementDAO.updateAchievement(achievement)
    }

    fun removeAchievement(achievement: Achievement){
        achievementDAO.removeAchievement(achievement)
    }
}