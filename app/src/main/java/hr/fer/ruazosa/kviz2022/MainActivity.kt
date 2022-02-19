package hr.fer.ruazosa.kviz2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as androidx.navigation.fragment.NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.background = null
        binding.navView.menu.getItem(1).isEnabled = false
        PreferenceManager.getDefaultSharedPreferences(this).apply {
            if (!getBoolean(OnboardingActivity.COMPLETED_ONBOARDING_PREF_NAME, false)) {
                val intent = Intent(this@MainActivity, OnboardingActivity::class.java)
                startActivity(intent)
            }
        }

        //onClickListeners for home and leaderboard items
        binding.bottomAppBar.setOnMenuItemClickListener{ item ->
            when(item.itemId) {
                R.id.homepageFragment -> {
                    if(navController.currentDestination?.id == R.id.homepageFragment) false
                    else{
                        navController.navigate(R.id.action_leaderboardFragment3_to_homepageFragment3)
                        true
                    }
                }
                R.id.leaderboardFragment -> {
                    if(navController.currentDestination?.id == R.id.leaderboardFragment) false
                    else{
                        navController.navigate(R.id.action_leaderboardFragment3_to_homepageFragment3)
                        true
                    }
                }
                else -> false
            }
        }
    }

    fun enterLobby(view: View) {
        val intent = Intent(this@MainActivity, GameActivity::class.java)
        startActivity(intent)
    }
}