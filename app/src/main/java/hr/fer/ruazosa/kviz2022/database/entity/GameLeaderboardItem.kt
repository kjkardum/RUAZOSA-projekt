package hr.fer.ruazosa.kviz2022.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GameLeaderboardItem (
    @PrimaryKey val userId: Int,
    @ColumnInfo(name = "UserName") val userName: String,
    @ColumnInfo(name = "NumberOfPoints") var numberOfPoints: Int?
    )