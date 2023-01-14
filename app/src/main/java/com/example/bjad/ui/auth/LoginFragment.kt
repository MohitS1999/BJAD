package com.example.bjad.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bjad.R
import com.example.bjad.databinding.FragmentLoginBinding
import com.example.bjad.util.UiState
import com.example.bjad.util.isValidEmail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

private const val TAG = "LoginFragment"

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel:AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.logInBtn.setOnClickListener {
            if (validation()) {
                    viewModel.login(
                        email = binding.emailEt.text.toString(),
                        password = binding.passEt.text.toString()
                    )
            }

        }

        binding.forgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPassword,Bundle().apply {  })
        }

        binding.signUplabel.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2,Bundle().apply {  })
        }
    }

    fun observer() {
        viewModel.login.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.registerProgress.visibility = View.VISIBLE
                }
                is UiState.Failure -> {
                    binding.registerProgress.visibility = View.INVISIBLE
                    Toast.makeText(context,state.error,Toast.LENGTH_SHORT).show()
                }
                is UiState.Success -> {
                    binding.registerProgress.visibility = View.INVISIBLE
                    Toast.makeText(context,state.data,Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_mainViewFragment,Bundle().apply {

                    })
                }
            }

        }
    }

    fun validation(): Boolean {
        var isValid = true

        if (binding.emailEt.text.isNullOrEmpty()){
            isValid = false;
            Toast.makeText(context,"Please Enter Email", Toast.LENGTH_SHORT).show()
        }else{
            if (!binding.emailEt.text.toString().isValidEmail()){
                isValid = false;
                Toast.makeText(context,"Please Enter Valid Email", Toast.LENGTH_SHORT).show()
            }
        }

        if (binding.passEt.text.isNullOrEmpty()){
            isValid = false;
            Toast.makeText(context,"Please Enter Password", Toast.LENGTH_SHORT).show()
        }
        return isValid;
    }

}