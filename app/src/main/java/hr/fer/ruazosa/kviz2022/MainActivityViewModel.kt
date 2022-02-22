package hr.fer.ruazosa.kviz2022

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    var userRepository: UserRepository
) : ViewModel() {

    private val _playGame = MutableLiveData<Boolean>()
    val playGame: LiveData<Boolean> get() = _playGame

    fun playAGame(){
        Timber.d("Clicked")
        _playGame.value = true
    }
}
