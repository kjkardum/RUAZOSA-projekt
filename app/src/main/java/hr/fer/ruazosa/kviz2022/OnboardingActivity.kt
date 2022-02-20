package hr.fer.ruazosa.kviz2022

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.account.*


@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    companion object {
        const val COMPLETED_ONBOARDING_PREF_NAME = "user_completed_onboarding"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        supportActionBar?.hide()

    }

    fun onFinishOnboarding() {
        PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().apply {
            putBoolean(COMPLETED_ONBOARDING_PREF_NAME, true)
            apply()
        }
        finish()
    }

    fun switchFragment(){

    }
}