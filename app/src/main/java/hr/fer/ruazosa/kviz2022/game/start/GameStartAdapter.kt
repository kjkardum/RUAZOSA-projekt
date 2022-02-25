package hr.fer.ruazosa.kviz2022.game.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.kviz2022.databinding.FollowersRowBinding
import hr.fer.ruazosa.kviz2022.leaderboard.LeaderboardClick
import hr.fer.ruazosa.kviz2022.network.dto.FollowerDTO
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import hr.fer.ruazosa.kviz2022.network.dto.UserDTO
import kotlinx.android.synthetic.main.followers_row.view.*

class FollowerClick(val block: (GameUserDTO) -> Unit) {
    fun onClick(follower: GameUserDTO) = block(follower)
}

class GameStartAdapter(val callback: FollowerClick):
    RecyclerView.Adapter<GameStartAdapter.GameStartHolder>() {

    class GameStartHolder(val binding: FollowersRowBinding):
        RecyclerView.ViewHolder(binding.root){}

    var followers: List<GameUserDTO> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameStartHolder {
        val withDataBinding = FollowersRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return GameStartHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: GameStartHolder, position: Int) {
        holder.binding.also {
            it.follower = followers[position]
            it.followerClick = callback
        }
    }

    override fun getItemCount(): Int = followers.count()

}

