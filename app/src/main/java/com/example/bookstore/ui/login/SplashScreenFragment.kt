package com.example.bookstore.ui.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R

class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            val action = SplashScreenFragmentDirections.splashScreenFragmenttoHomeToDetail()
            findNavController().navigate(action)
        },3000)
        return inflater.inflate(R.layout.fragment_splashscreen, container, false)

    }
}