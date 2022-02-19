package hr.fer.ruazosa.kviz2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.background = null
        binding.navView.menu.getItem(1).isEnabled = false
        PreferenceManager.getDefaultSharedPreferences(this).apply {
            if (!getBoolean(OnboardingActivity.COMPLETED_ONBOARDING_PREF_NAME, false)) {
                val intent = Intent(this@MainActivity, OnboardingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun enterLobby(view: View) {
        val intent = Intent(this@MainActivity, GameActivity::class.java)
        startActivity(intent)
    }
}