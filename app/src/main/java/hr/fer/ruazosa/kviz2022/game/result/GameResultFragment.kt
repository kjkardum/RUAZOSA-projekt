package hr.fer.ruazosa.kviz2022.game.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FragmentGameResultBinding
import hr.fer.ruazosa.kviz2022.databinding.FragmentGameStartBinding
import hr.fer.ruazosa.kviz2022.game.start.GameStartViewModel

@AndroidEntryPoint
class GameResultFragment : Fragment() {
    private val viewModel by viewModels<GameResultViewModel>()

    private lateinit var viewDataBinding: FragmentGameResultBinding

    private var viewModelGameResultAdapter: GameResultAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.results.observe(viewLifecycleOwner){
            it?.apply {
                viewModelGameResultAdapter?.results = this.leaderboard
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewDataBinding = FragmentGameResultBinding.inflate(
            inflater, container, false
        ).apply {
            viewModelBinding = viewModel
        }
        viewDataBinding.lifecycleOwner = this


        return viewDataBinding.root
    }
}