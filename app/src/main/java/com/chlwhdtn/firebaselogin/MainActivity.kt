package com.chlwhdtn.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.chlwhdtn.firebaselogin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // 마법
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        auth = Firebase.auth

        binding.regBtn.setOnClickListener {
            auth.createUserWithEmailAndPassword(binding.regId.text.toString(), binding.regPw.text.toString()).addOnCompleteListener {
                if(it.isSuccessful)
                    binding.textview.text = "가입 성공"
                else
                    binding.textview.text = "가입 실패"
            }
        }

        binding.loginBtn.setOnClickListener {
            auth.signInWithEmailAndPassword(binding.loginId.text.toString(), binding.loginPw.text.toString()).addOnCompleteListener {
                if(it.isSuccessful)
                    binding.textview.text = "로그인 성공"
                else
                    binding.textview.text = "로그인 실패 : " + it.exception
            }
        }
    }
}