package hr.fer.ruazosa.kviz2022.game.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FragmentGameQuestionBinding
import hr.fer.ruazosa.kviz2022.databinding.FragmentGameResultBinding
import hr.fer.ruazosa.kviz2022.game.result.GameResultViewModel

@AndroidEntryPoint
class GameQuestionFragment : Fragment() {
    private val viewModel by viewModels<GameQuestionViewModel>()

    private lateinit var viewDataBinding: FragmentGameQuestionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewDataBinding = FragmentGameQuestionBinding.inflate(
            inflater, container, false
        ).apply {
            viewModelBinding = viewModel
        }
        viewDataBinding.lifecycleOwner = this

        return viewDataBinding.root
    }
}