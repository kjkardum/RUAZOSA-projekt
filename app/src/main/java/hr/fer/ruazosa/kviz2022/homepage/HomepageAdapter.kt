package hr.fer.ruazosa.kviz2022.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.ContinueGameRowBinding
import hr.fer.ruazosa.kviz2022.databinding.FollowsuggestionsItemBinding
import hr.fer.ruazosa.kviz2022.network.dto.*
import hr.fer.ruazosa.kviz2022.network.dto.game.GameDTO

class FollowerClick(val block: (GameUserDTO) -> Unit) {
    fun onClick(follower: GameUserDTO) = block(follower)
}

class GameClick(val block: (GameDTO) -> Unit){
    fun onClick(game: GameDTO) = block(game)
}

class GameAdapter(val callback: GameClick):
        RecyclerView.Adapter<GameAdapter.GameViewHolder>(){
    class GameViewHolder(val viewDataBinding: ContinueGameRowBinding):
        RecyclerView.ViewHolder(viewDataBinding.root){}

    var games: List<GameDTO> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val withDataBinding = ContinueGameRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return GameViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.viewDataBinding.also{
            it.game = games[position]
            it.gameClick = callback
        }
    }

    override fun getItemCount(): Int = games.count()

}


class FollowerAdapter(val callback: FollowerClick) :
    RecyclerView.Adapter<FollowerAdapter.FollowersViewHolder>() {

    class FollowersViewHolder(val viewDataBinding: FollowsuggestionsItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) { }

    var followers: List<GameUserDTO> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val withDataBinding = FollowsuggestionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
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