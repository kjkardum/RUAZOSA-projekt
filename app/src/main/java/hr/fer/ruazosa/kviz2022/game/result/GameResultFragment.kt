package hr.fer.ruazosa.kviz2022.game.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.results.observe(viewLifecycleOwner){
            it?.apply {
                viewModelGameResultAdapter?.results = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentGameResultBinding.inflate(
            inflater, container, false
        ).apply {
            viewModelBinding = viewModel
        }

        viewModelGameResultAdapter = GameResultAdapter()

        viewDataBinding.root.findViewById<RecyclerView>(R.id.GameResultRecyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelGameResultAdapter
        }

        viewModel.gameEnd.observe(viewLifecycleOwner){
            it?.let {
                if (it) {
                    findNavController().navigate(R.id.action_gameResultFragment2_to_homepageFragment)
                }
            }
        }

        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
    }
}