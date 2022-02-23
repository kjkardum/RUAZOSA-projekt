package hr.fer.ruazosa.kviz2022.game.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.kviz2022.databinding.LeaderboardRowBinding
import hr.fer.ruazosa.kviz2022.game.start.GameStartAdapter
import hr.fer.ruazosa.kviz2022.network.dto.game.GameLeaderboardResponseDTO
import hr.fer.ruazosa.kviz2022.network.dto.game.GameLeaderboardResponseItemDTO

class GameResultAdapter: RecyclerView.Adapter<GameResultAdapter.GameResultHolder>() {
    class GameResultHolder(val binding: LeaderboardRowBinding):
            RecyclerView.ViewHolder(binding.root){}

    var results: List<GameLeaderboardResponseItemDTO> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameResultHolder {
        val withDataBinding = LeaderboardRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return GameResultHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: GameResultHolder, position: Int) {
        holder.binding.also {
            it.item = results[position]
        }
    }

    override fun getItemCount(): Int = results.count()


}