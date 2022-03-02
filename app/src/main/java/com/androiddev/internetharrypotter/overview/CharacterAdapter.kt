package com.androiddev.internetharrypotter.overview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddev.internetharrypotter.databinding.CharacterItemDesignBinding
import com.androiddev.internetharrypotter.network.CharacterModel

class CharacterAdapter : androidx.recyclerview.widget.ListAdapter<CharacterModel, CharacterAdapter.CharactersViewHolder>(DiffCallBack) {

    var onClick: (CharacterModel) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.CharactersViewHolder {
        return CharactersViewHolder(CharacterItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharactersViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character, onClick)
    }


    class CharactersViewHolder(private val binding: CharacterItemDesignBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterModel, onClick: (CharacterModel) -> Unit = {} ) {

            binding.characterModelItemXml = character
            binding.executePendingBindings()
            //executePendingBindings binding adapter ı hızlı bir şekilde etkinkeştirmek içindir

            binding.root.setOnClickListener {
                onClick(character)
            }
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<CharacterModel>() {

        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }


    }




}