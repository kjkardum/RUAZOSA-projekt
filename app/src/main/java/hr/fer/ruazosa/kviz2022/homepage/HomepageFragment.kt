package hr.fer.ruazosa.kviz2022.homepage

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R
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
        val view: View = inflater!!.inflate(R.layout.fragment_homepage, container, false)
        val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val usrName: String? = pref.getString("Username", "")
        val h: TextView = view.findViewById(R.id.HelloUser)
        h.text = usrName
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