package com.example.bjad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.Model.User
import com.example.bjad.databinding.FragmentRegisterBinding
import com.example.util.isValidEmail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    val TAG: String = "RegisterFragment"
    lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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

            }
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
        TODO("Not yet implemented")
    }

    fun getUserObj(): User {
        return User(
            userName = binding.username.text.toString(),
            email = binding.eMailSign.text.toString()
        )
    }
}