package hr.fer.ruazosa.kviz2022.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Achievement (
    @PrimaryKey(autoGenerate=true) val id: Int,
    @ColumnInfo(name = "AchievementName") val achievementName: String,
    @ColumnInfo(name = "Progress") var progress: Int?,
    @ColumnInfo(name = "Target") val target: Int,
    @ColumnInfo(name = "Status") var status: Boolean
)