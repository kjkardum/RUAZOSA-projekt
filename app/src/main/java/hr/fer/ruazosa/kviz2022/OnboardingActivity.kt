package hr.fer.ruazosa.kviz2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    companion object {
        const val COMPLETED_ONBOARDING_PREF_NAME = "user_completed_onboarding"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        this.findViewById<Button>(R.id.finish_onboarding_button).setOnClickListener {
            onFinishOnboarding()
        }
    }

    private fun onFinishOnboarding() {
        PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().apply {
            putBoolean(COMPLETED_ONBOARDING_PREF_NAME, true)
            apply()
        }
        finish()
    }
}