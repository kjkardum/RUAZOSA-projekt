package hr.fer.ruazosa.kviz2022.homepage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FragmentHomepageBinding
import timber.log.Timber

@AndroidEntryPoint
class HomepageFragment : Fragment() {

    private val homepageViewModel by viewModels<HomepageViewModel>()

    private lateinit var viewDataBinding: FragmentHomepageBinding

    private var viewModelFollowerAdapter: FollowerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homepageViewModel.suggestedFollowers.observe(viewLifecycleOwner) {
            it?.apply {
                viewModelFollowerAdapter?.followers = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewDataBinding = FragmentHomepageBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = homepageViewModel
        }

        viewModelFollowerAdapter = FollowerAdapter(FollowerClick {
            homepageViewModel.followUser(it)
        })
        viewDataBinding.root.findViewById<RecyclerView>(R.id.followerRecyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelFollowerAdapter
        }

        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        homepageViewModel.logoutAction.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    homepageViewModel.finishLogout()
                    val intent = Intent(activity, OnboardingActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
            }
        }
        return viewDataBinding.root
    }
}