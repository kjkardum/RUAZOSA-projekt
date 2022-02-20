package hr.fer.ruazosa.kviz2022.onboarding

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
        val view =  inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
        }, 2000)

        return view
    }

}