package hr.fer.ruazosa.kviz2022.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FragmentFirstScreenBinding
import hr.fer.ruazosa.kviz2022.databinding.FragmentHomepageBinding
import hr.fer.ruazosa.kviz2022.homepage.HomepageViewModel

@AndroidEntryPoint
class FirstScreen : Fragment() {

    private val firstScreenViewModel by viewModels<FirstScreenViewModel>()

    private lateinit var firstScreenBinding: FragmentFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        firstScreenBinding = FragmentFirstScreenBinding.inflate(
            inflater, container, false
        ).apply {
            viewFirstScreen = firstScreenViewModel
        }
        firstScreenBinding.lifecycleOwner = viewLifecycleOwner
        firstScreenViewModel.gotoNext.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    val pager = activity?.findViewById<ViewPager2>(R.id.viewPager)
                    pager?.currentItem = 1
                }
            }
        }

        return firstScreenBinding.root
    }

}