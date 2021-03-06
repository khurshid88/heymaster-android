package com.heymaster.heymaster.role.master.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.heymaster.heymaster.databinding.ItemPortfolioBinding
import com.heymaster.heymaster.model.masterprofile.Portfolio

class DetailsPortfolioAdapter :ListAdapter<Portfolio, DetailsPortfolioAdapter.DetailsPortfolioViewHolder>(
    DetailsPortfolioItemDifCallBack()) {

    inner class DetailsPortfolioViewHolder(private val binding: ItemPortfolioBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Portfolio) {

        }

    }

    private class DetailsPortfolioItemDifCallBack : DiffUtil.ItemCallback<Portfolio>() {
        override fun areItemsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsPortfolioViewHolder {
        val view = ItemPortfolioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsPortfolioViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsPortfolioViewHolder, position: Int) {
        val portfolio = getItem(position)
        holder.bind(portfolio)
    }


}