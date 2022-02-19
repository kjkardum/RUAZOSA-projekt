package hr.fer.ruazosa.kviz2022.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hr.fer.ruazosa.kviz2022.database.entity.User
import hr.fer.ruazosa.kviz2022.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val getUser: User
    private val repo: UserRepository

    init {
        val dao = UserDatabase.getInstance(application).userDao()
        repo = UserRepository(dao)
        getUser = repo.getUser
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUser(user)
        }
    }

}