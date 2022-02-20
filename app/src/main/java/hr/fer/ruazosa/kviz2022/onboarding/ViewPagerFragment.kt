package hr.fer.ruazosa.kviz2022.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.onboarding.screens.FirstScreen
import hr.fer.ruazosa.kviz2022.onboarding.screens.SecondScreen
import hr.fer.ruazosa.kviz2022.onboarding.screens.ThirdScreen
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        view.viewPager.adapter = adapter

        return view
    }
}