package hr.fer.ruazosa.kviz2022.onboarding.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FragmentFirstScreenBinding
import hr.fer.ruazosa.kviz2022.databinding.FragmentSecondScreenBinding


class SecondScreen : Fragment() {

    private val secondScreenViewModel by viewModels<SecondScreenViewModel>()

    private lateinit var secondScreenBinding: FragmentSecondScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        secondScreenBinding = FragmentSecondScreenBinding.inflate(
            inflater, container, false
        ).apply {
            viewSecondScreen = secondScreenViewModel
        }
        secondScreenBinding.lifecycleOwner = viewLifecycleOwner

        secondScreenViewModel.gotoPrev.observe(viewLifecycleOwner){
            it?.let {
                if (it) {
                    val pager = activity?.findViewById<ViewPager2>(R.id.viewPager)
                    pager?.currentItem = pager?.currentItem?.minus(1)!!
                }
            }
        }

        secondScreenViewModel.gotoNext.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    val pager = activity?.findViewById<ViewPager2>(R.id.viewPager)
                    pager?.currentItem = 2
                }
            }
        }

        return secondScreenBinding.root
    }
}