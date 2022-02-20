package hr.fer.ruazosa.kviz2022.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import hr.fer.ruazosa.kviz2022.database.entity.Achievement
import hr.fer.ruazosa.kviz2022.repository.AchievementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AchievementViewModel(application: Application) : AndroidViewModel(application) {
    val getAchievement: Achievement
    private val repo: AchievementRepository

    init {
        val dao = UserDatabase.getInstance(application).achievementDao()
        repo = AchievementRepository(dao)
        getAchievement = repo.getAchievement
    }

    fun addAchievement(achievement: Achievement){
        viewModelScope.launch(Dispatchers.IO){
            repo.addAchievement(achievement)
        }
    }
}