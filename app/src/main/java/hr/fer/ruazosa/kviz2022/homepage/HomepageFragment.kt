package hr.fer.ruazosa.kviz2022.homepage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.database.UserViewModel
import hr.fer.ruazosa.kviz2022.databinding.FragmentHomepageBinding
import kotlinx.android.synthetic.main.fragment_homepage.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class HomepageFragment : Fragment() {

    private val homepageViewModel by viewModels<HomepageViewModel>()

    private lateinit var viewDataBinding: FragmentHomepageBinding

    private lateinit var hUserViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        CoroutineScope(Dispatchers.IO).launch() {
            hUserViewModel = ViewModelProvider(this@HomepageFragment)[UserViewModel::class.java]
            view.HelloUser.text = "Hello "  + hUserViewModel.getUser.userName
        }
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewDataBinding = FragmentHomepageBinding.inflate( inflater, container, false
        ).apply {
            viewModel = homepageViewModel
        }

        val view: View = inflater.inflate(R.layout.fragment_homepage, container, false)

        viewDataBinding.lifecycleOwner = this
        homepageViewModel.navigateToSomewhere.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    Timber.i("Navigated somewhere")
                    homepageViewModel.doneNavigating()
                }
            }
        }
        return view
    }
}