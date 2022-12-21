package com.example.bjad.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.bjad.R
import com.example.bjad.databinding.FragmentForgotPasswordBinding
import com.example.bjad.util.UiState
import com.example.bjad.util.isValidEmail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_forgot_password.*


private const val TAG = "ForgotPassword"
@AndroidEntryPoint
class ForgotPassword : Fragment() {

    lateinit var binding:FragmentForgotPasswordBinding
    val viewModel:AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.sendBtn.setOnClickListener{
            if (validation()){
                viewModel.forgotPassword(binding.emailEt.text.toString())
            }
        }
    }

    private fun observer(){
        viewModel.forgot.observe(viewLifecycleOwner) { state ->
            when (state){
                is UiState.Loading -> {
                    binding.registerProgress.visibility = View.VISIBLE
                }
                is UiState.Failure -> {
                    binding.registerProgress.visibility = View.INVISIBLE
                    Toast.makeText(context,state.error, Toast.LENGTH_SHORT).show()
                }
                is UiState.Success -> {
                    binding.registerProgress.visibility = View.INVISIBLE
                    Toast.makeText(context,state.data,Toast.LENGTH_SHORT).show()
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
        return isValid;
    }

}