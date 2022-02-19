package hr.fer.ruazosa.kviz2022.database.DAO

import androidx.room.*
import hr.fer.ruazosa.kviz2022.database.entity.User

@Dao
interface UserDAO {
    @Query ("SELECT * FROM User")
    fun getUser(): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun removeUser(user: User)
}