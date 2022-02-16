package hr.fer.ruazosa.kviz2022.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.forms.*
import hr.fer.ruazosa.kviz2022.forms.StandardResponseForm
import hr.fer.ruazosa.kviz2022.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login){

    override fun onCreate(savedInstanceState: Bundle?) {
        var viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_login, container, false)
        val reg: TextView = view.findViewById(R.id.RegisterNow)
        val btn: Button = view.findViewById(R.id.finish_onboarding_button)
        val mail: String = view.findViewById<TextView?>(R.id.LoginEmail).text.toString()
        val pass: String = view.findViewById<TextView?>(R.id.LoginPassword).text.toString()
        reg.setOnClickListener {
            (this.activity as OnboardingActivity?)?.switchFragment()
        }
        btn.setOnClickListener {
            makeLogin(mail, pass)
        }
        return view
    }

    private fun makeLogin(mail: String, pass: String){
        val logForm = LoginForm(mail, pass)
        val req: Call<StandardResponseForm> = Network.accountService.authenticateAccount(logForm)
        req.enqueue(
            object : Callback<StandardResponseForm> {
                override fun onResponse(
                    call: Call<StandardResponseForm>,
                    response: Response<StandardResponseForm>
                ) {
                    (activity as OnboardingActivity?)?.onFinishOnboarding()
                    val newUser = response.body()
                    val code = response.code()
                    if (code == 200){
                        if (newUser != null) {
                            Toast.makeText(context, newUser.toString(), Toast.LENGTH_SHORT).show()
                            (activity as OnboardingActivity?)?.onFinishOnboarding()
                        }
                    }
                }

                override fun onFailure(call: Call<StandardResponseForm>, t: Throwable) {
                    (activity as OnboardingActivity?)?.onFinishOnboarding()
                    Toast.makeText(context, "Failed to Register", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}