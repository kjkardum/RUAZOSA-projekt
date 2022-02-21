package hr.fer.ruazosa.kviz2022.onboarding

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.onboarding.screens.FirstScreen
import hr.fer.ruazosa.kviz2022.onboarding.screens.SecondScreen
import hr.fer.ruazosa.kviz2022.onboarding.screens.ThirdScreen
import kotlinx.android.synthetic.main.fragment_view_pager.view.*
import kotlinx.coroutines.delay

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingDone()){
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, 2000)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun onBoardingDone(): Boolean{
        return requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE).getBoolean("finished", false)
    }

}