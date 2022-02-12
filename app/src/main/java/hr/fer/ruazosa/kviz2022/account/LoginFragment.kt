package hr.fer.ruazosa.kviz2022.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_login, container, false)
        val reg: TextView = view.findViewById(R.id.RegisterNow)
        val btn: Button = view.findViewById(R.id.finish_onboarding_button)
        reg.setOnClickListener {
            (this.activity as OnboardingActivity?)?.switchFragment()
        }
        btn.setOnClickListener {
            (this.activity as OnboardingActivity?)?.onFinishOnboarding()
        }

        return view
    }




}