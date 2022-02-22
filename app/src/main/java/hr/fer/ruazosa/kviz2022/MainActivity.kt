package hr.fer.ruazosa.kviz2022

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.databinding.ActivityMainBinding
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    private lateinit var mainActivityMainBinding: ActivityMainBinding

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupWithNavController(binding.navView, navController!!)
        binding.navView.background = null
        binding.navView.menu.getItem(1).isEnabled = false
        if (!viewModel.userRepository.isAuthenticated()) {
            val intent = Intent(this@MainActivity, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.fab.setOnClickListener{
            navController!!.navigate(R.id.action_homepageFragment_to_gameStartFragment2)
        }
    }

}