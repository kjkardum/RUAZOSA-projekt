package hr.fer.ruazosa.kviz2022.onboarding.screens

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.viewpager.widget.ViewPager


@BindingAdapter("next")
fun goToNextScreen(view: ViewPager, data: LiveData<Boolean>){
    if (data.value == true){
        view.currentItem = 1
    }
}