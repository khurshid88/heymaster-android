package com.heymaster.heymaster.global.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heymaster.heymaster.R
import com.heymaster.heymaster.databinding.ItemServiceBinding
import com.heymaster.heymaster.model.home.Category
import com.heymaster.heymaster.utils.RandomColor

class CategoryAdapter: ListAdapter<Category, CategoryAdapter.ServiceViewHolder>(ServiceItemDiffCallback()) {

    var itemClickListener: ((Category) -> Unit)? = null

    inner class ServiceViewHolder(private val binding: ItemServiceBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.apply {
                tvServiceName.text = category.name
                Glide.with(icServices).load(category.photoUrl).placeholder(RandomColor.randomColor()).into(icServices)

            }

            binding.root.setOnClickListener {
                itemClickListener?.invoke(category)
            }

        }
    }

    private class ServiceItemDiffCallback: DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }
}