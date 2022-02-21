package hr.fer.ruazosa.kviz2022

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    var userRepository: UserRepository
) : ViewModel() {

}
