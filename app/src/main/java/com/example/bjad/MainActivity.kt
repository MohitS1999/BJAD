package com.example.bjad

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.bjad.databinding.ActivityMainBinding
import kotlin.math.sign

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signUp.setOnClickListener {
            binding.signUp.background = resources.getDrawable(R.drawable.switch_trcks,null)
            binding.signUp.setTextColor(resources.getColor(R.color.textColor,null))
            binding.logIn.background = null
            binding.signUpLayout.visibility = View.VISIBLE
            binding.logInLayout.visibility = View.GONE
            binding.logIn.setTextColor(resources.getColor(R.color.pinkColor,null))
            binding.loggedIn.setText("Sign Up");
        }
        binding.logIn.setOnClickListener {
            binding.signUp.background = null
            binding.signUp.setTextColor(resources.getColor(R.color.pinkColor,null))
            binding.logIn.background = resources.getDrawable(R.drawable.switch_trcks,null)
            binding.signUpLayout.visibility = View.GONE
            binding.logInLayout.visibility = View.VISIBLE
            binding.logIn.setTextColor(resources.getColor(R.color.textColor,null))
            binding.loggedIn.setText("Log In");
        }


        

    }
}