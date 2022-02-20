package hr.fer.ruazosa.kviz2022.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.fer.ruazosa.kviz2022.database.DAO.*
import hr.fer.ruazosa.kviz2022.database.entity.*

@Database(entities = [User::class, Achievement::class, GameLeaderboardItem::class], version = 2)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDAO
    abstract fun achievementDao(): AchievementDAO
    abstract fun gameLeaderboardItemDao(): GameLeaderboardItemDAO

    companion object {

        @Volatile
        private var inst: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return inst ?: synchronized(this){
                inst ?: buildDatabase(context).also { inst = it }
            }
        }

         private fun buildDatabase(context: Context): UserDatabase{
            return Room.databaseBuilder(context, UserDatabase::class.java, "user").build()
        }
    }
}
