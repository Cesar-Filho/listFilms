package com.example.listfilms.lists

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listfilms.R

class GradeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var gradeName: TextView = view.findViewById(R.id.grade_name)
    var gradeImage: ImageView = view.findViewById(R.id.grade_image)
}