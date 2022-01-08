package hr.fer.ruazosa.kviz2022.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.databinding.FragmentLeaderboardBinding
import javax.inject.Inject

@AndroidEntryPoint
class LeaderboardFragment : Fragment() {

    private val leaderboardViewModel by viewModels<LeaderboardViewModel>()

    private lateinit var viewDataBinding: FragmentLeaderboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentLeaderboardBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = leaderboardViewModel
        }
        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
    }
}