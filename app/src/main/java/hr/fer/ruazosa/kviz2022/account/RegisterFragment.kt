package hr.fer.ruazosa.kviz2022.account

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.google.android.material.textfield.TextInputEditText
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.forms.RegisterForm
import hr.fer.ruazosa.kviz2022.forms.ResponseForm
import hr.fer.ruazosa.kviz2022.network.Network
import org.json.JSONObject
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
            makeRegistration(mail, usr, pass, rpass, view)
        }
        return view
    }

    private fun makeRegistration(mail : String, usr: String, pass: String, rpass: String, view: View){
        Log.d("INFO", "Making form")
        val regform = RegisterForm(mail, usr, pass, rpass)
        Log.d("INFO", "Sending request")
        val req: Call<ResponseForm> = Network.accountService.registerNewAccount(regform)
        req.enqueue(
            object : Callback<ResponseForm> {
                override fun onResponse(
                    call: Call<ResponseForm>,
                    response: Response<ResponseForm>
                ) {
                    Log.d("SUCCESS", "Server responded")
                    if (!response.isSuccessful){
                        val objError = JSONObject(response.errorBody()!!.string()).getJSONObject("errors")
                        when {
                            objError.getString("Email") != null -> {
                                view.findViewById<TextInputEditText?>(R.id.RegisterEmail).background = ColorDrawable(resources.getColor(R.color.error))
                            }
                            objError.getString("Password") != null -> {
                                view.findViewById<TextInputEditText?>(R.id.RegisterPassword).background = ColorDrawable(resources.getColor(R.color.error))
                            }
                            objError.getString("UserName") != null -> {
                                view.findViewById<TextInputEditText?>(R.id.RegisterUsername).background = ColorDrawable(resources.getColor(R.color.error))
                            }
                            objError.getString("ConfirmPassword") != null -> {
                                view.findViewById<TextInputEditText?>(R.id.RegisterRepeatPassword).background = ColorDrawable(resources.getColor(R.color.error))
                            }
                        }
                    } else {
                        if (response.code() == 200) {
                            val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
                            val editor: SharedPreferences.Editor = pref.edit()
                            editor.putString("Username", usr)
                            editor.commit()
                            editor.putString("Email", mail)
                            editor.commit()
                            Toast.makeText(context, "welcome", Toast.LENGTH_SHORT).show()
                            (activity as OnboardingActivity?)?.onFinishOnboarding()
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseForm>, t: Throwable) {
                    Toast.makeText(context, "Failed to Register please try again", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}