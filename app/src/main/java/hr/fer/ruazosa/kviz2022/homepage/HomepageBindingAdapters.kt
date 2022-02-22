package hr.fer.ruazosa.kviz2022.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FollowsuggestionsItemBinding
import hr.fer.ruazosa.kviz2022.network.dto.*

class FollowerClick(val block: (GameUserDTO) -> Unit) {
    fun onClick(follower: GameUserDTO) = block(follower)
}

class FollowerAdapter(val callback: FollowerClick) : RecyclerView.Adapter<FollowersViewHolder>() {
    var followers: List<GameUserDTO> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val withDataBinding: FollowsuggestionsItemBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                FollowersViewHolder.LAYOUT,
                parent,
                false)
        return FollowersViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.follower = followers[position]
            it.followerClick = callback
        }
    }

    override fun getItemCount() = followers.size

}

class FollowersViewHolder(val viewDataBinding: FollowsuggestionsItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.followsuggestions_item
        }
    }