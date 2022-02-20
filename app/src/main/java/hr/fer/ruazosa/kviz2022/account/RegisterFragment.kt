package hr.fer.ruazosa.kviz2022.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R


class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        val btn: Button = view.findViewById(R.id.registerButton)
        val mail: String = view.findViewById<TextView?>(R.id.RegisterEmail).text.toString()
        val usr: String = view.findViewById<TextView?>(R.id.RegisterUsername).text.toString()
        val pass: String = view.findViewById<TextView?>(R.id.RegisterPassword).text.toString()
        val rpass: String = view.findViewById<TextView?>(R.id.RegisterRepeatPassword).text.toString()
        btn.setOnClickListener {
            makeRegistration(mail, usr, pass, rpass)
        }
        return view
    }

    private fun makeRegistration(mail : String, usr: String, pass: String, rpass: String){
        /** TODO **/
        (this.activity as OnboardingActivity?)?.onFinishOnboarding()
    }
}