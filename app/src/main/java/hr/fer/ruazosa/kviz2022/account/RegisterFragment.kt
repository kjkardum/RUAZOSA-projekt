package hr.fer.ruazosa.kviz2022.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FragmentLoginBinding
import hr.fer.ruazosa.kviz2022.databinding.FragmentRegisterBinding

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val registerFragmentViewModel by viewModels<RegisterFragmentViewModel>()

    private lateinit var viewDataBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewDataBinding = FragmentRegisterBinding.inflate(
            inflater, container, false
        ).apply {
            registerViewModel = registerFragmentViewModel
        }

        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        registerFragmentViewModel.registration.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return viewDataBinding.root
    }

}