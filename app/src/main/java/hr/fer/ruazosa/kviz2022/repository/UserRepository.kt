package hr.fer.ruazosa.kviz2022.repository

import hr.fer.ruazosa.kviz2022.database.DAO.UserDAO
import hr.fer.ruazosa.kviz2022.database.entity.User

class UserRepository(private val userDAO: UserDAO) {

    val getUser: User = userDAO.getUser()

    fun addUser(user: User){
        userDAO.addUser(user)
    }

    fun updateUser(user: User){
        userDAO.updateUser(user)
    }

    fun removeUser(user: User){
        userDAO.removeUser(user)
    }
}