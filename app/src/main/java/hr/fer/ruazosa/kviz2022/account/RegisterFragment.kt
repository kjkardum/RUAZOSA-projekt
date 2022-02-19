package hr.fer.ruazosa.kviz2022.account

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.database.UserDatabase
import hr.fer.ruazosa.kviz2022.database.UserViewModel
import hr.fer.ruazosa.kviz2022.database.entity.User
import hr.fer.ruazosa.kviz2022.network.DTOs.RegisterDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.ResponseDTO
import hr.fer.ruazosa.kviz2022.network.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : Fragment(R.layout.fragment_register) {

    var cont: Context? = null

    private lateinit var hUsrViewModel: UserViewModel

    private val ref = this
    private fun addToDatabase(user: User){
        CoroutineScope(Dispatchers.IO).launch() {
            hUsrViewModel = ViewModelProvider(ref)[UserViewModel::class.java]
            hUsrViewModel.addUser(user)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        val btn: Button = view.findViewById(R.id.registerButton)
        val mail = view.findViewById<TextInputLayout?>(R.id.RegisterEmailWrap).editText!!.text
        val usr= view.findViewById<TextInputLayout?>(R.id.RegisterUsernameWrap).editText!!.text
        val pass = view.findViewById<TextInputLayout?>(R.id.RegisterPasswordWrap).editText!!.text
        val rpass = view.findViewById<TextInputLayout?>(R.id.RegisterRepeatPasswordWrap).editText!!.text
        btn.setOnClickListener {
            makeRegistration(mail.toString(), usr.toString(), pass.toString(), rpass.toString(), view)
        }
        return view
    }

    private fun makeRegistration(email : String, userName: String, password: String, confirmPassword: String, view: View){
        val regform = RegisterDTO(email, userName, password, confirmPassword)
        Log.d("INFO", "Sending request")
        val req: Call<ResponseDTO> = Network.accountService.registerNewAccount(regform)
        req.enqueue(
            object : Callback<ResponseDTO> {
                override fun onResponse(
                    call: Call<ResponseDTO>,
                    response: Response<ResponseDTO>
                ) {
                    if (!response.isSuccessful){
                        val objError = JSONObject(response.errorBody()!!.string()).getJSONObject("errors")
                        val keys = objError.keys()
                        while (keys.hasNext()){
                            //TODO
                            /*when {
                                keys.next() == "Email" -> {
                                    view.findViewById<TextInputEditText>(R.id.RegisterEmail).setBackgroundResource(R.color.error)
                                }
                                keys.next() == "UserName" -> {
                                    view.findViewById<TextInputEditText>(R.id.RegisterUsername).setBackgroundResource(R.color.error)
                                }
                                keys.next() == "ConfirmPassword" -> {
                                    view.findViewById<TextInputEditText>(R.id.RegisterRepeatPassword).setBackgroundResource(R.color.error)
                                }
                                keys.next() == "Password" -> {
                                    view.findViewById<TextInputEditText>(R.id.RegisterPassword).setBackgroundResource(R.color.error)
                                }
                            } */
                            Log.d("ITEM", "onto next element")
                        }
                    } else {
                        if (response.code() == 200) {
                            val usr = User(12, userName, email)
                            addToDatabase(usr)
                            Toast.makeText(context, "welcome", Toast.LENGTH_SHORT).show()
                            (activity as OnboardingActivity?)?.onFinishOnboarding()
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                    Toast.makeText(context, "Failed to Register please try again", Toast.LENGTH_SHORT).show()
                }
            }
        )

    }

}