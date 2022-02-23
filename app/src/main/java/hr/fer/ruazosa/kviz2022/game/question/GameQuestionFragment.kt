package hr.fer.ruazosa.kviz2022.game.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        viewModel.answerValid.observe(viewLifecycleOwner){
            it?.let {
                if (it) {
                    Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.answerInvalid.observe(viewLifecycleOwner){
            it?.let {
                if (it) {
                    Toast.makeText(context, "Incorrect", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.finishGame.observe(viewLifecycleOwner) {
            if (it == true) {
                Toast.makeText(context, "Game finished", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_gameQuestionFragment2_to_gameResultFragment2)
            }
        }

        return viewDataBinding.root
    }
}