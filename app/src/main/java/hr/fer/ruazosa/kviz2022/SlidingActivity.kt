package hr.fer.ruazosa.kviz2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.preference.PreferenceManager
import androidx.viewpager.widget.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import android.content.Context

@AndroidEntryPoint
class SlidingActivity : PagerAdapter() {

    override fun getCount(): Int {
        return 3;
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

    }

}