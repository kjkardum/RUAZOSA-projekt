package hr.fer.ruazosa.kviz2022.homepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.databinding.FragmentHomepageBinding
import timber.log.Timber

@AndroidEntryPoint
class HomepageFragment : Fragment() {

    private val homepageViewModel by viewModels<HomepageViewModel>()

    private lateinit var viewDataBinding: FragmentHomepageBinding

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
        viewDataBinding.lifecycleOwner = this
        homepageViewModel.navigateToSomewhere.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    Timber.i("Navigated somewhere")
                    homepageViewModel.doneNavigating()
                }
            }
        }
        return viewDataBinding.root
    }
}