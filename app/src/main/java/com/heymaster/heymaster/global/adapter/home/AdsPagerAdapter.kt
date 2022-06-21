package com.heymaster.heymaster.global.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.heymaster.heymaster.R
import com.heymaster.heymaster.model.home.Advertising

class AdsPagerAdapter: RecyclerView.Adapter<AdsPagerAdapter.AdsViewHolder>(){

    private val ads: ArrayList<Advertising> = ArrayList()

    inner class AdsViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(ads: Advertising) {
            val tvTitle = view.findViewById<TextView>(R.id.tvAdsTitle)
            val tvBody = view.findViewById<TextView>(R.id.tvAdsBody)
            ads.`object`.forEach {
                tvTitle.text = it.title
                tvBody.text = it.body
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ads, parent, false)
        return AdsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        val ads = ads[position]
        holder.bind(ads)
    }

    override fun getItemCount(): Int {
        return ads.size
    }

    fun submitAds(advertising: ArrayList<Advertising>) {
        ads.clear()
        ads.addAll(advertising)
        notifyDataSetChanged()
    }
}