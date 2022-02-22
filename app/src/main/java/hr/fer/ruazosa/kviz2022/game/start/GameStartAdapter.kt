package hr.fer.ruazosa.kviz2022.game.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.kviz2022.databinding.FollowersRowBinding

class GameStartAdapter: RecyclerView.Adapter<GameStartAdapter.GameStartHolder>() {

    class GameStartHolder(val binding: FollowersRowBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameStartHolder {
        return GameStartHolder(FollowersRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GameStartHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}