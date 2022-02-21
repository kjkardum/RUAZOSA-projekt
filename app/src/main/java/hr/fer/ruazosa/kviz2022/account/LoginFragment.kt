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
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FragmentHomepageBinding
import hr.fer.ruazosa.kviz2022.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login){

    private val loginFragmentViewModel by viewModels<LoginFragmentViewModel>()

    private lateinit var viewDataBinding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewDataBinding = FragmentLoginBinding.inflate(
            inflater, container, false
        ).apply {
            loginViewModel = loginFragmentViewModel
        }
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        loginFragmentViewModel.logged.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        loginFragmentViewModel.register.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                }
            }
        }

        return viewDataBinding.root
    }

}