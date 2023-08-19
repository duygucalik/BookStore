package com.example.bookstore.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookstore.MainApplication
import com.example.bookstore.common.viewBinding
import com.example.bookstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding by viewBinding (ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MainApplication.provideRetrofit(this)
    }
}