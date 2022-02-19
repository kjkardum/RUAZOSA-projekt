package hr.fer.ruazosa.kviz2022.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.database.UserViewModel
import hr.fer.ruazosa.kviz2022.databinding.FragmentLeaderboardBinding
import kotlinx.android.synthetic.main.fragment_homepage.view.*
import kotlinx.android.synthetic.main.fragment_leaderboard.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LeaderboardFragment : Fragment() {

    private val leaderboardViewModel by viewModels<LeaderboardViewModel>()

    private lateinit var viewDataBinding: FragmentLeaderboardBinding

    private lateinit var hUserViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        CoroutineScope(Dispatchers.IO).launch() {
            hUserViewModel = ViewModelProvider(this@LeaderboardFragment)[UserViewModel::class.java]
            view.LeaderboardUsername.text = hUserViewModel.getUser.userName
        }
        super.onViewCreated(view, savedInstanceState)
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