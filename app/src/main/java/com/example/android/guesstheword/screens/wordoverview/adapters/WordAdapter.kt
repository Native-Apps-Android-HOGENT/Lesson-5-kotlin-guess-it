package com.example.android.guesstheword.screens.wordoverview.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.guesstheword.databinding.ListItemWordBinding
import com.example.android.guesstheword.domain.Word

class WordAdapter: ListAdapter<Word,
        RecyclerView.ViewHolder>(WordDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordViewHolder(ListItemWordBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val word = getItem(position)
        (holder as WordViewHolder).bind(word)
    }

    class WordViewHolder(private val binding : ListItemWordBinding):
            RecyclerView.ViewHolder(binding.root){

        fun bind(item : Word){
            binding.apply {
                word = item
                executePendingBindings()
            }
        }
    }
}



private class WordDiffCallback : DiffUtil.ItemCallback<Word>() {

    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }
}
