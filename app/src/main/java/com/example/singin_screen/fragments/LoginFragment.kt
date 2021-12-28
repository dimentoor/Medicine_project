package com.example.singin_screen.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.singin_screen.MainActivity
import com.example.singin_screen.R
import com.example.singin_screen.adapter.ProductsAdapter
import com.example.singin_screen.data.DataSourse
import com.example.singin_screen.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    companion object{
        fun newInstance() = LoginFragment()
}

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.btnEnter.setOnClickListener {
            (activity as MainActivity).navigateToFragment(
                TransitionFragment.newInstance()
            )
        }

    }

}