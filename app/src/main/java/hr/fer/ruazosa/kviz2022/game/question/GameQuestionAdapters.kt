package hr.fer.ruazosa.kviz2022.game.question

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("time")
fun setTime(view: TextView, data: Int){
    view.text = "TIME: ${data}"
}