package com.dawastatistics.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dawastatistics.BR
import com.dawastatistics.R
import com.dawastatistics.databinding.PostListItemBinding
import com.dawastatistics.model.PostInfo


class PostListAdapter(var context: Context) : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private  var list: List<PostInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PostListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.post_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    fun setAdapterList(list: List<PostInfo> ){
        this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.postmodel, data)
            binding.executePendingBindings()
        }
    }
}