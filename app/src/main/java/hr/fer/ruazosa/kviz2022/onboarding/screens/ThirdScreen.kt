package hr.fer.ruazosa.kviz2022.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
                    pager?.currentItem = pager?.currentItem?.minus(1)!!
                }
            }
        }

        thirdScreenViewModel.gotoLogin.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
                    val editor = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE).edit()
                    editor.putBoolean("finished", true)
                    editor.apply()
                }
            }
        }

        return thirdScreenBinding.root
    }

}