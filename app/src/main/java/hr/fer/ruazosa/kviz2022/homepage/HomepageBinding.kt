package hr.fer.ruazosa.kviz2022.homepage

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

/**
 * Demo binding adapter to show counter on homepage
 */
@BindingAdapter("counterText")
fun setCounterText(view: TextView, data: LiveData<Int>){
    view.text = "Counter value: '${data.value}'"
}

@BindingAdapter("userNameText")
fun setUserNameText(view: TextView, data: LiveData<String>) {
    if (!data.value.isNullOrEmpty()) {
        view.text = "Username: '${data.value}'"
    } else {
        view.text = "Getting username from Api"
    }
}