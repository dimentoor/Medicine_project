package com.example.singin_screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.singin_screen.R
import com.example.singin_screen.model.Products

typealias OnProductsClickListener = (Products) -> Unit

class ProductsAdapter (
        private val medicines : List<Products>,
        private val listener: OnProductsClickListener,
): RecyclerView.Adapter<ProductsAdapter.MedicinesVH>(){

        override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
        ): MedicinesVH{
              val layoutInflater = LayoutInflater.from(parent.context)
              return  MedicinesVH(
                      layoutInflater.inflate(
                              R.layout.activity_medicines,parent,false), listener
              )
        }

        override fun onBindViewHolder(holder: MedicinesVH, position: Int) = holder.bind(medicines[position])

        override fun getItemCount(): Int = medicines.size

    class MedicinesVH (
        view : View,
        listener : OnProductsClickListener
    ):RecyclerView.ViewHolder(view){
        private val inCover = view.findViewById<ImageView>(R.id.iv_item)
        private val tvName = view.findViewById<TextView>(R.id.tv_title)
        private val tvDescription = view.findViewById<TextView>(R.id.tv_description)

        private lateinit var product : Products

        init {
            view.setOnClickListener{listener(product)}
        }

        fun bind(product : Products){
            this.product = product
            tvName.text = product.name
            tvDescription.text = product.description
            // tvCost
            inCover.setImageResource(product.picture_medicines)
        }
    }
}

