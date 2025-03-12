package com.sample.golf.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.golf.databinding.ItemRowGolfCoursesBinding
import com.sample.golf.domain.model.GolfCourse


class GolfCoursesAdapter(
    private val onSelect : (GolfCourse) -> Unit
) : ListAdapter<GolfCourse, GolfCoursesAdapter.GolfCourseViewHolder>(GolfCourseDiffCallback) {

  companion object{
      val GolfCourseDiffCallback = object : DiffUtil.ItemCallback<GolfCourse>() {
          override fun areItemsTheSame(oldItem: GolfCourse, newItem: GolfCourse): Boolean {
              return oldItem.id == newItem.id
          }

          override fun areContentsTheSame(oldItem: GolfCourse, newItem: GolfCourse): Boolean {
              return oldItem == newItem
          }
      }
  }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GolfCourseViewHolder {
        val binding = ItemRowGolfCoursesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GolfCourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GolfCourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }




    inner class GolfCourseViewHolder(private val binding: ItemRowGolfCoursesBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(course: GolfCourse) {
            binding.tvCourse.text = course.courseName
            binding.tvClub.text = course.clubName
            binding.tvLocation.text = course.location.address
            binding.root.setOnClickListener {
                onSelect.invoke(course)
            }
        }
    }

}
