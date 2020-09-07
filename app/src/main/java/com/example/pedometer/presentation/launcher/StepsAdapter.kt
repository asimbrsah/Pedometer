package com.example.pedometer.presentation.launcher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pedometer.databinding.ItemStepsBinding

class StepsAdapter :
    ListAdapter<String, StepsAdapter.StepsViewHolder>(StepsAdapterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        return StepsViewHolder(
            ItemStepsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StepsViewHolder(
        private val itemStepsBinding: ItemStepsBinding
    ) : RecyclerView.ViewHolder(itemStepsBinding.root) {

        fun bind(
            string: String
        ) {
            itemStepsBinding.tvSensorData.text = "$string Steps"
        }
    }


    object StepsAdapterDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }
}