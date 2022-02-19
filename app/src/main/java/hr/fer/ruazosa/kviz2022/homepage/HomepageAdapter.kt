package hr.fer.ruazosa.kviz2022.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.kviz2022.R
import hr.fer.ruazosa.kviz2022.database.entity.User
import kotlinx.android.synthetic.main.fragment_homepage.view.*


class HomepageAdapter: RecyclerView.Adapter<HomepageAdapter.HomepageHolder>() {

    private var user = emptyList<User>()

    class HomepageHolder(item: View): RecyclerView.ViewHolder(item){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomepageAdapter.HomepageHolder {
        return HomepageHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_homepage, parent, false))
    }

    override fun onBindViewHolder(holder: HomepageAdapter.HomepageHolder, position: Int) {
        val currentUser = user[position]
        holder.itemView.HelloUser.text = currentUser.userName.toString()
    }

    override fun getItemCount(): Int {
        return user.size
    }

    fun setData(user: List<User>){
        this.user = user
    }


}