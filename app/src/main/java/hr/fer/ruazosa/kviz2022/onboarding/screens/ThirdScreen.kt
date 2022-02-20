package hr.fer.ruazosa.kviz2022.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FragmentSecondScreenBinding
import hr.fer.ruazosa.kviz2022.databinding.FragmentThirdScreenBinding
import timber.log.Timber


class ThirdScreen : Fragment() {

    private val thirdScreenViewModel by viewModels<ThirdScreenViewModel>()

    private lateinit var thirdScreenBinding: FragmentThirdScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        thirdScreenBinding = FragmentThirdScreenBinding.inflate(
            inflater, container, false
        ).apply {
            viewThirdScreen = thirdScreenViewModel
        }
        thirdScreenBinding.lifecycleOwner = viewLifecycleOwner

        thirdScreenViewModel.gotoPrev.observe(viewLifecycleOwner){
            it?.let {
                if (it) {
                    val pager = activity?.findViewById<ViewPager2>(R.id.viewPager)
                    pager?.currentItem = 2
                }
            }
        }

        thirdScreenViewModel.gotoLogin.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    Timber.d("TODO")
                }
            }
        }

        return thirdScreenBinding.root
    }

}