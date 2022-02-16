package hr.fer.ruazosa.kviz2022.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.fer.ruazosa.kviz2022.GameActivity
import hr.fer.ruazosa.kviz2022.R

class GameEndFragment: Fragment(R.layout.fragment_game_end) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun endGame(){
        (activity as GameActivity?)?.returnToHomepage()
    }
}