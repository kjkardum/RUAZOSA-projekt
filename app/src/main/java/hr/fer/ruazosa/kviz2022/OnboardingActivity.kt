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
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.slide_in,R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            addToBackStack(null)
            replace(R.id.logFrag, LoginFragment())
            commit()
        }

    }

    fun onFinishOnboarding() {
        PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().apply {
            putBoolean(COMPLETED_ONBOARDING_PREF_NAME, true)
            apply()
        }
        finish()
    }

    fun switchFragment(){
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.slide_in,R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            addToBackStack(null)
            replace(R.id.logFrag, RegisterFragment())
            commit()
        }
    }
}