package com.example.bjad.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bjad.Model.User
import com.example.bjad.R
import com.example.bjad.databinding.FragmentRegisterBinding
import com.example.bjad.util.UiState
import com.example.bjad.util.isValidEmail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    val TAG: String = "RegisterFragment"
    lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<AuthViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.signInBtn.setOnClickListener {
            if (validation()){
                viewModel.register(
                    email =  binding.eMailSign.text.toString(),
                    password = binding.passwordSign.text.toString(),
                    user = getUserObj()
                )
            }
        }
        binding.logInlabel.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment2_to_loginFragment,Bundle().apply {  })
        }
    }

    fun validation(): Boolean {
        var isValid = true

        if(binding.username.text.isNullOrEmpty()){
            isValid = false
            Toast.makeText(context,"Please Enter Name",Toast.LENGTH_SHORT).show()
        }
        if (binding.eMailSign.text.isNullOrEmpty()){
            isValid = false;
            Toast.makeText(context,"Please Enter Email",Toast.LENGTH_SHORT).show()
        }else{
            if (!binding.eMailSign.text.toString().isValidEmail()){
                isValid = false;
                Toast.makeText(context,"Please Enter Valid Email",Toast.LENGTH_SHORT).show()
            }
        }

        if (binding.passwordSign.text.isNullOrEmpty()){
            isValid = false;
            Toast.makeText(context,"Please Enter Password",Toast.LENGTH_SHORT).show()
        }else{
            if (binding.passwordSign.text.toString().length < 8){
                isValid = false;
                Toast.makeText(context,"Length of password should be greater than 9",Toast.LENGTH_SHORT).show()
            }
        }
        return isValid;
    }

    fun observer() {
        viewModel.register.observe(viewLifecycleOwner) { state ->
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
                    findNavController().navigate(R.id.action_registerFragment2_to_mainViewFragment,Bundle().apply {

                    })
                }
            }

        }
    }

    fun getUserObj(): User {
        return User(
            id = "",
            userName = binding.username.text.toString(),
            email = binding.eMailSign.text.toString()
        )
    }
}