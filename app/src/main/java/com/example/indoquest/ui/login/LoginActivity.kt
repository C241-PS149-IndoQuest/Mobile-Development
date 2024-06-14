package com.example.indoquest.ui.login

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
import com.example.indoquest.api.LoginBody
import com.example.indoquest.databinding.ActivityLoginBinding
import com.example.indoquest.databinding.ActivitySignUpBinding
import com.example.indoquest.sharedprefrences.SharedPreferencesManager
import com.example.indoquest.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferencesManager = SharedPreferencesManager(this)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }
        displayAnimation()

        val etEmail = binding.etEmail
        val etPassword = binding.etPassword
//        val loginBtn = binding.btnLogin
        val signUpTv = binding.tvSignup

        etEmail.validateInput('1')
        etPassword.validateInput('2')

//        loginBtn.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

        signUpTv.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        validateButtonLogin()
    }

    @SuppressLint("Recycle")
    fun displayAnimation(){
        val loginTitle = ObjectAnimator.ofFloat(binding.tvLoginTitle, View.ALPHA, 1f).setDuration(500)
        val loginLogo = ObjectAnimator.ofFloat(binding.ivLoginLogo, View.ALPHA, 1f).setDuration(500)
        val loginSubtitle = ObjectAnimator.ofFloat(binding.tvLoginSubtitle, View.ALPHA, 1f).setDuration(500)
        val edEmail = ObjectAnimator.ofFloat(binding.etEmail, View.ALPHA, 1f).setDuration(500)
        val edPassword = ObjectAnimator.ofFloat(binding.etPassword, View.ALPHA, 1f).setDuration(500)
        val cbRememberMe = ObjectAnimator.ofFloat(binding.cbRememberMe, View.ALPHA, 1f).setDuration(500)
        val tvforgotPassword = ObjectAnimator.ofFloat(binding.tvForgotPassword, View.ALPHA, 1f).setDuration(500)
        val loginBtn = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)
        val tvDontHaveAccount = ObjectAnimator.ofFloat(binding.tvDontHaveAccount, View.ALPHA, 1f).setDuration(500)
        val tvSignUp = ObjectAnimator.ofFloat(binding.tvSignup, View.ALPHA, 1f).setDuration(500)

        val together1= AnimatorSet().apply{
            playTogether(loginTitle, loginLogo, loginSubtitle)
        }

        val together2= AnimatorSet().apply{
            playTogether(cbRememberMe, tvforgotPassword)
        }

        val together3= AnimatorSet().apply{
            playTogether(loginBtn, tvDontHaveAccount, tvSignUp)
        }

        AnimatorSet().apply{
            playSequentially(together1, edEmail, edPassword, together2, together3)
            start()
        }
    }

    private fun validateButtonLogin() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail
            val password = binding.etPassword

            if(email.text.toString().isEmpty() || password.text.toString().isEmpty())
                Toast.makeText(this, "Pastikan anda telah mengisi input email dan password!", Toast.LENGTH_SHORT).show()

            if (email.isEmailValid && password.isPasswordValid) {
                showLoading(true)
//                Toast.makeText(this, "${email.isEmailValid} && ${password.isPasswordValid}", Toast.LENGTH_LONG).show()
                viewModel.postLogin(LoginBody(email.text.toString(), password.text.toString()))
                viewModel.loginResponse.observe(this) { response ->
                    Log.d("Login", response.toString())
                    if (!response.error) {
                        showLoading(false)
                        sharedPreferencesManager.saveData(response.loginResult.token, response.loginResult.name, email.text.toString())
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        showLoading(false)
                        Toast.makeText(this, "Login Failed. Try Again!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state)
            binding.pbProgressBar.visibility = View.VISIBLE
        else
            binding.pbProgressBar.visibility = View.GONE
    }
}