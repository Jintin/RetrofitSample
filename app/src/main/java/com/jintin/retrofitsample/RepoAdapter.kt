package com.jintin.retrofitsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jintin.retrofitsample.databinding.AdapterRepoBinding

class RepoAdapter(diffCheck: DiffUtil.ItemCallback<Repo> = RepoDiffCheck) :
    ListAdapter<Repo, RepoHolder>(diffCheck) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        return RepoHolder(
            AdapterRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class RepoHolder(private val binding: AdapterRepoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repo: Repo) {
        binding.name.text = repo.name
        binding.star.text = repo.starCount.toString()
    }
}

object RepoDiffCheck : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}