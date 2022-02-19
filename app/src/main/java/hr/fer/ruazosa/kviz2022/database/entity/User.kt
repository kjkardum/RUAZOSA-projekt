package hr.fer.ruazosa.kviz2022.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey (autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "UserName") val userName: String?,
    @ColumnInfo(name = "Email") val email: String?
)