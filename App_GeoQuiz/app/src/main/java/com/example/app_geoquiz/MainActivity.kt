package com.example.app_geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var questions: ArrayList<Question>
    var indice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadQuestions()
        setupViews()
    }

    private fun nextQuestion(btNext : Button) {
        indice++
        val tvSentence = findViewById<TextView>(R.id.tvSentence)
        tvSentence.text = questions[indice].sentence
    }

    private fun isLastQuestion(btNext : Button, tvSentence : TextView, isCorrect: Boolean) {
        if(indice < questions.size - 1)
        {
            nextQuestion(btNext)
        }
        else if (!isCorrect) {
            Toast.makeText(this, "Te encuentras en la última pregunta.", Toast.LENGTH_LONG).show()
        }
    }

    private fun isAnswerCorrect(btNext : Button, tvSentence : TextView, isCorrect : Boolean) {
        if(isCorrect)
        {
            Toast.makeText(this, "Correcto", Toast.LENGTH_LONG).show()
            isLastQuestion(btNext, tvSentence, true)
        }
        else{
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadQuestions() {
        questions = ArrayList()
        var question = Question("Pregunta 1\nEs Lima la capital de Perú", true)
        questions.add(question)

        questions.add(Question("Pregunta 2\nEs Bolivia capital de Perú", false))
        questions.add(Question("Pregunta 3\nEs La Paz capital de Perú", false))
        questions.add(Question("Pregunta 4\nEs Bolivia capital de Perú", false))
        questions.add(Question("Pregunta 5\nEs La Paz capital de Bolivia", true))
        questions.add(Question("Pregunta 6\nEs Quito capital de Ecuador", true))
        questions.add(Question("Pregunta 7\nEs Buenos Aires capital de Perú", false))
    }

    private fun setupViews() {
        val btYes = findViewById<Button>(R.id.btYes)
        val btNo = findViewById<Button>(R.id.btNo)
        val btNext = findViewById<Button>(R.id.btNext)
        val btBack = findViewById<Button>(R.id.btBack)
        val tvSentence = findViewById<TextView>(R.id.tvSentence)

        tvSentence.text = questions[indice].sentence


        btYes.setOnClickListener {
            isAnswerCorrect(btNext, tvSentence, questions[indice].answer)
        }

        btNo.setOnClickListener {
            isAnswerCorrect(btNext, tvSentence, !questions[indice].answer)
        }

        btNext.setOnClickListener {
            isLastQuestion(btNext, tvSentence, false)
        }

        btBack.setOnClickListener {
            if(indice > 0)
            {
                indice--
                tvSentence.text = questions[indice].sentence
            }
            else{
                Toast.makeText(this, "Te encuentras en la primera pregunta.", Toast.LENGTH_LONG).show()
            }
        }
    }
}