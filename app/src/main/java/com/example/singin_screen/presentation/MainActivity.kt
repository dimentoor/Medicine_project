package com.example.singin_screen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.singin_screen.R
import com.example.singin_screen.databinding.ActivityMainBinding
import com.example.singin_screen.presentation.fragments.LoginFragment

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        if(savedInstanceState == null){
            navigateToFragment(LoginFragment.newInstance())
        }
    }

    fun navigateToFragment(fmt: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView,fmt)
            .addToBackStack(fmt.javaClass.name)
            .commit()
    }
}