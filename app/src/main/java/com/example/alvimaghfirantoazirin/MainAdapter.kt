package com.example.alvimaghfirantoazirin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alvimaghfirantoazirin.databinding.ItemDataBinding

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val listData = ArrayList<Entity>()
    private var onItemClick: OnItemClick? = null

    fun setData(data: ArrayList<Entity>){
        this.listData.clear()
        this.listData.addAll(data)
    }

    inner class MainViewHolder(private val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Entity){
            with(binding){
                tvUserId.text = data.userId.toString()
                tvId.text = data.id.toString()
                tvTitle.text = data.title
                tvComp.text = data.status

                itemView.setOnClickListener {
                    onItemClick?.onItemClicked(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(view)

    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    fun setOnItemClick(onItemClick: OnItemClick){
        this.onItemClick = onItemClick
    }

    interface OnItemClick {
        fun onItemClicked(data: Entity)
    }
}