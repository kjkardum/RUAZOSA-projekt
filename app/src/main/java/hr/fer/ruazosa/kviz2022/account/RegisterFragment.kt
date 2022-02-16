package hr.fer.ruazosa.kviz2022.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.forms.*
import hr.fer.ruazosa.kviz2022.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_register, container, false)
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
        val regform = RegisterForm(mail, usr, pass, rpass)
        val req: Call<StandardResponseForm> = Network.accountService.registerNewAccount(regform)
        req.enqueue(
            object : Callback<StandardResponseForm> {
                override fun onResponse(
                    call: Call<StandardResponseForm>,
                    response: Response<StandardResponseForm>
                ) {
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
                    Log.d("FAILED", t.toString())
                    Toast.makeText(context, "Failed to Register", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}