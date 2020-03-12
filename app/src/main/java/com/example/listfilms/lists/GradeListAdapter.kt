package com.example.listfilms.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listfilms.R
import com.example.listfilms.model.Grade
import com.squareup.picasso.Picasso

class GradeListAdapter(var data: List<Grade>) : RecyclerView.Adapter<GradeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item, parent, false)

        return GradeViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val currentGrade = data[position]

        holder.gradeName.text = currentGrade.name

        Picasso.get()
            .load(currentGrade.imagePath)
            .resize(150, 150)
            .centerCrop()
            .into(holder.gradeImage)
    }

}