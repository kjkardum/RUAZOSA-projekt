package hr.fer.ruazosa.kviz2022.leaderboard

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.databinding.FollowsuggestionsItemBinding
import hr.fer.ruazosa.kviz2022.databinding.LeaderboardRowBinding
import hr.fer.ruazosa.kviz2022.homepage.FollowerAdapter
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import hr.fer.ruazosa.kviz2022.network.dto.game.GameLeaderboardResponseItemDTO


class LeaderboardClick(val block: (GameLeaderboardResponseItemDTO) -> Unit) {
    fun onClick(user: GameLeaderboardResponseItemDTO) = block(user)
}

class LeaderboardAdapter(val callback: LeaderboardClick) :
    RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>(){

    class LeaderboardViewHolder(val viewDataBinding: LeaderboardRowBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) { }

    var items: List<GameLeaderboardResponseItemDTO> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val withDataBinding = LeaderboardRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return LeaderboardViewHolder(withDataBinding)
    }
    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.item = items[position]
            it.itemClick = callback
        }
    }
    override fun getItemCount(): Int = items.count()
}

@BindingAdapter("number")
fun setNumber(view: TextView, value: Int?) {
    if (value != null && view.text != "" + value)
        view.text = "" + value
}

@BindingAdapter("game_points")
fun setGamePoints(view: TextView, data: Int){
    view.text = "Total: ${data} points"
}

@BindingAdapter("position_background")
fun setPositionBackground(view: ConstraintLayout, position: Int) {
    if (position < 3) {
        view.background= ColorDrawable(R.color.md_theme_dark_onPrimary)
    } else {
        view.background = null
    }}