package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var diceImage1:ImageView
    lateinit var diceImage2:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultText: TextView = findViewById(R.id.result_text)
        resultText.text = "Dice rolle"

        diceImage1 = findViewById(R.id.dice_image1)
        diceImage2 = findViewById(R.id.dice_image1)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        //val countUpButton: Button = findViewById(R.id.contup_button)
        //countUpButton.setOnClickListener{countUp()}
    }

    private fun countUp() {

        if(result_text.text != "Dice roller" && result_text.text != "6"){
            var rand:Int =  result_text.text.toString().toInt() + 1
            result_text.text = rand.toString()
        }
    }

    private fun rollDice() {
        var randomInt = (1..6).random()
        when(randomInt) {
            1 -> dice_image1.setImageResource(R.drawable.dice_1)
            2 -> dice_image1.setImageResource(R.drawable.dice_2)
            3 -> dice_image1.setImageResource(R.drawable.dice_3)
            4 -> dice_image1.setImageResource(R.drawable.dice_4)
            5 -> dice_image1.setImageResource(R.drawable.dice_5)
            else -> dice_image1.setImageResource(R.drawable.dice_6)
        }

        randomInt = (1..6).random()
        when(randomInt) {
            1 -> dice_image2.setImageResource(R.drawable.dice_1)
            2 -> dice_image2.setImageResource(R.drawable.dice_2)
            3 -> dice_image2.setImageResource(R.drawable.dice_3)
            4 -> dice_image2.setImageResource(R.drawable.dice_4)
            5 -> dice_image2.setImageResource(R.drawable.dice_5)
            else -> dice_image2.setImageResource(R.drawable.dice_6)
        }
        //result_text.text = randomInt.toString()

        //Toast.makeText(this, "button clicked",
          //      Toast.LENGTH_SHORT).show()
    }
}
