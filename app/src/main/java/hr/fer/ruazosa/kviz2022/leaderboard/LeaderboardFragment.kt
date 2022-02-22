package hr.fer.ruazosa.kviz2022.leaderboard

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FragmentLeaderboardBinding
import hr.fer.ruazosa.kviz2022.homepage.FollowerAdapter
import hr.fer.ruazosa.kviz2022.homepage.FollowerClick
import javax.inject.Inject

@AndroidEntryPoint
class LeaderboardFragment : Fragment() {

    private val leaderboardViewModel by viewModels<LeaderboardViewModel>()

    private lateinit var viewDataBinding: FragmentLeaderboardBinding

    private var viewModelLeadeboardAdapter: LeaderboardAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leaderboardViewModel.leaderboardItems.observe(viewLifecycleOwner) {
            it?.apply {
                viewModelLeadeboardAdapter?.items = this
            }
        }
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

        viewModelLeadeboardAdapter = LeaderboardAdapter(LeaderboardClick {
            var builder = AlertDialog.Builder(context)
            builder.setMessage("Do you want to follow this user?")
                .setPositiveButton("Yes") { dialog, _ ->
                    leaderboardViewModel.followUser(it)
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            var alert = builder.create()
            alert.show()
        })
        viewDataBinding.root.findViewById<RecyclerView>(R.id.LeaderboardList).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelLeadeboardAdapter
        }

        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
    }
}