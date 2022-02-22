package hr.fer.ruazosa.kviz2022.game.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.kviz2022.databinding.FollowersRowBinding
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import kotlinx.android.synthetic.main.followers_row.view.*

class FollowerClick(val select: (GameUserDTO) -> Unit) {
    fun onClick(follower: GameUserDTO) = select(follower)
}

class GameStartAdapter: RecyclerView.Adapter<GameStartAdapter.GameStartHolder>() {

    class GameStartHolder(val binding: FollowersRowBinding): RecyclerView.ViewHolder(binding.root){}

    var followers: List<GameUserDTO> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameStartHolder {
        return GameStartHolder(FollowersRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GameStartHolder, position: Int) {
        val currentFollower = followers[position]
        holder.itemView.Follower.text = currentFollower.userName
    }

    override fun getItemCount(): Int {
        return followers.size
    }
}