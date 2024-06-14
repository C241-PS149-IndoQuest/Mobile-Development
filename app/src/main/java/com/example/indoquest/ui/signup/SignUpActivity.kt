package com.example.indoquest.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.indoquest.MainActivity
import com.example.indoquest.R
import com.example.indoquest.api.RegisterBody
import com.example.indoquest.databinding.ActivityMainBinding
import com.example.indoquest.databinding.ActivitySignUpBinding
import com.example.indoquest.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding : ActivitySignUpBinding
    private var isErrorEnabled = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }
        displayAnimation()

        val edUsername = binding.edUsername
        val edEmail = binding.edEmail
        val edPassword = binding.edPassword
        val loginTv = binding.tvLogin

        edUsername.validateInput('0')
        edEmail.validateInput('1')
        edPassword.validateInput('2')

        loginTv.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        validateConfirmPassword()
        validateButtonSignUp()
    }

    @SuppressLint("Recycle")
    fun displayAnimation(){
        val signUpTitle = ObjectAnimator.ofFloat(binding.tvSignupTitle, View.ALPHA, 1f).setDuration(500)
        val signUpLogo = ObjectAnimator.ofFloat(binding.ivSignupLogo, View.ALPHA, 1f).setDuration(500)
        val signUpSubtitle = ObjectAnimator.ofFloat(binding.tvSignupSubtitle, View.ALPHA, 1f).setDuration(500)
        val edUsername = ObjectAnimator.ofFloat(binding.edUsername, View.ALPHA, 1f).setDuration(500)
        val edEmail = ObjectAnimator.ofFloat(binding.edEmail, View.ALPHA, 1f).setDuration(500)
        val edPassword = ObjectAnimator.ofFloat(binding.edPassword, View.ALPHA, 1f).setDuration(500)
        val edConfirmPassword = ObjectAnimator.ofFloat(binding.edConfirmPassword, View.ALPHA, 1f).setDuration(500)
        val signUpBtn = ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1f).setDuration(500)
        val tvHaveAccount = ObjectAnimator.ofFloat(binding.tvHaveAccount, View.ALPHA, 1f).setDuration(500)
        val tvLogin = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1f).setDuration(500)

        val together1= AnimatorSet().apply{
            playTogether(signUpTitle, signUpLogo, signUpSubtitle)
        }

        val together2= AnimatorSet().apply{
            playTogether(signUpBtn, tvHaveAccount, tvLogin)
        }

        AnimatorSet().apply{
            playSequentially(together1, edUsername, edEmail, edPassword, edConfirmPassword, together2)
            start()
        }
    }

    private fun validateButtonSignUp() {
        binding.btnSignup.setOnClickListener {
            val email = binding.edEmail
            val username = binding.edUsername
            val password = binding.edPassword
            val confirmPassword = binding.edConfirmPassword
            if(email.text.toString().isEmpty() || password.text.toString().isEmpty() || username.text.toString().isEmpty())
                Toast.makeText(this, "Pastikan anda telah mengisi input email, username, dan password!", Toast.LENGTH_SHORT).show()

            if(email.isEmailValid && password.isPasswordValid && username.isUsernameValid
                && (confirmPassword.text.toString() == password.text.toString())){
                showLoading(true)
                viewModel.postRegister(RegisterBody(username.text.toString(), username.text.toString(),email.text.toString(), password.text.toString()))
                viewModel.registerResponse.observe(this){
                    Log.d("TAG", "Hasil Register : $it")
                    if(!it.error){
                        showLoading(false)
                        startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                        finish()
                    }else{
                        showLoading(false)
                        Toast.makeText(this, "Register Failed. Try again!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun validateConfirmPassword() : Boolean{
        var errorMessage : String? = null
        val value1 = binding.edConfirmPassword.text.toString()
        val value2 = binding.edPassword.text.toString()
        if (value1.isEmpty()){
            errorMessage = "Confirm Password is required"
        }else if(value1 != value2){
            errorMessage = "Confirm Password does not match"
        }

        if(errorMessage != null){
            binding.edConfirmPassword.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun showLoading(state: Boolean) {
        if (state)
            binding.pbProgressBar.visibility = View.VISIBLE
        else
            binding.pbProgressBar.visibility = View.GONE
    }
}