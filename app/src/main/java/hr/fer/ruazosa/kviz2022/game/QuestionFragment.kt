package hr.fer.ruazosa.kviz2022.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hr.fer.ruazosa.kviz2022.GameActivity
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.R
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment(R.layout.fragment_question) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_question, container, false)
        val btn: FloatingActionButton = view.findViewById(R.id.NextQuestion)
        val answer: String = view.findViewById<TextView?>(R.id.AnswerField).text.toString()
        btn.setOnClickListener {
            checkQuestion(answer)
        }
        return view
    }

    private fun checkQuestion(answer:String){
        CountdownTimer

    }
}