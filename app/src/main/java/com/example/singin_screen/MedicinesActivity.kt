package com.example.singin_screen

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.singin_screen.adapter.ProductsAdapter
import com.example.singin_screen.data.DataSourse
import com.example.singin_screen.databinding.ActivityLoginBinding
import com.example.singin_screen.databinding.RvMedicinesBinding
import javax.sql.DataSource

class MedicinesActivity : AppCompatActivity() {

    companion object{
        const val KEY_NAME = "name"
        const val KEY_DESCRIPTION = "description"
        const val KEY_ICON_RES_ID = "iconResID"
    }

    private lateinit var binding: RvMedicinesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RvMedicinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMedicines.layoutManager = LinearLayoutManager(this)
        binding.rvMedicines.adapter = ProductsAdapter(DataSourse.medicines){
                (name,description,iconResId) ->
        val intent = Intent(this,MedicinesActivity::class.java)
        intent.putExtra(KEY_NAME, name)
        intent.putExtra(KEY_DESCRIPTION, description)
        intent.putExtra(KEY_ICON_RES_ID, iconResId)
        startActivity(intent)
        }
    }
}

