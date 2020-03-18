package com.example.computersciencequiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.computersciencequiz.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    data class Question(
        val text: String,
        val answers: List<String>,
        val correctAnswer: String)

    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "Quel est le langage informatique le plus courant utilisé pour écrire les pages web ?",
            answers = listOf("HTML", "HTTP", "Java"),
            correctAnswer = "HTML"),
        Question(text = "À quoi est égal 1 octet ?",
            answers = listOf("À 4 bites", "À 8 bits", "À 32 bits"),
            correctAnswer = "À 8 bits"),
        Question(text = "Qu’est-ce qu’une adresse IP ?",
            answers = listOf("Un numéro qui identifie chaque matériel informatique (ordinateur, routeur, imprimante) connecté à un réseau informatique",
                "Le protocole de communication utilisé sur Internet",
                "L’adresse d’un site web, commençant par \"http://\""),
            correctAnswer = "Un numéro qui identifie chaque matériel informatique (ordinateur, routeur, imprimante) connecté à un réseau informatique"),
        Question(text = "Qu’est-ce qu’un CPU ?",
            answers = listOf("Un processeur", "Une carte video", "Un disque dur"),
            correctAnswer = "Un processeur")
    )


    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = questions.size

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.correctAnswer) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
                        view.findNavController()
                            .navigate(R.id.action_gameFragment_to_wonFragment)
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    view.findNavController()
                        .navigate(R.id.action_gameFragment_to_lostFragment)
                }
            }
        }
        return binding.root
    }


    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        //answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_question, questionIndex + 1, numQuestions)
    }
}
