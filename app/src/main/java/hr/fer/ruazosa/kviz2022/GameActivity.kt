package hr.fer.ruazosa.kviz2022

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruazosa.kviz2022.game.GameEndFragment
import hr.fer.ruazosa.kviz2022.game.QuestionFragment


class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    fun startGame(view: View) {
        val layout: LinearLayout = findViewById(R.id.StartingView)
        layout.removeAllViews()
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.slide_in,R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            addToBackStack(null)
            replace(R.id.gameFrag, QuestionFragment())
            commit()
        }
    }

    fun gotoGameEnd(){
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.slide_in,R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            addToBackStack(null)
            replace(R.id.gameFrag, GameEndFragment())
            commit()
        }
    }

    fun returnToHomepage(){
        val intent = Intent(this@GameActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}