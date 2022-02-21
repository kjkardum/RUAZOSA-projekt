package hr.fer.ruazosa.kviz2022.game.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.account.LoginFragmentViewModel
import hr.fer.ruazosa.kviz2022.databinding.FragmentGameStartBinding
import hr.fer.ruazosa.kviz2022.databinding.FragmentLoginBinding

@AndroidEntryPoint
class GameStartFragment : Fragment() {

    private val viewModel by viewModels<GameStartViewModel>()

    private lateinit var viewDataBinding: FragmentGameStartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewDataBinding = FragmentGameStartBinding.inflate(
            inflater, container, false
        ).apply {
            viewModelBinding = viewModel
        }
        viewDataBinding.lifecycleOwner = this

        return viewDataBinding.root
    }
}