package com.example.calculatorymbong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculatorymbong.R

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentInput = ""
    private var operand1: Double? = null
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        // Number Buttons
        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2,
            R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8,
            R.id.button9
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener { numberClicked(it) }
        }

        // Operator Buttons
        findViewById<Button>(R.id.buttonPlus).setOnClickListener { operatorClicked("+") }
        findViewById<Button>(R.id.buttonMinus).setOnClickListener { operatorClicked("-") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { operatorClicked("*") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { operatorClicked("/") }

        findViewById<Button>(R.id.buttonEqual).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.buttonClear).setOnClickListener { clearInput() }
    }

    private fun numberClicked(view: View) {
        val button = view as Button
        currentInput += button.text.toString()
        tvResult.text = currentInput
    }

    private fun operatorClicked(op: String) {
        if (currentInput.isNotEmpty()) {
            operand1 = currentInput.toDouble()
            operator = op
            currentInput = ""
        }
    }

    private fun calculateResult() {
        if (operand1 != null && operator != null && currentInput.isNotEmpty()) {
            val operand2 = currentInput.toDouble()
            val result = when (operator) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> if (operand2 != 0.0) operand1!! / operand2 else {
                    clearInput()
                    return
                }
                else -> 0.0
            }

            tvResult.text = result.toString()
            currentInput = ""
            operand1 = result
            operator = null
        }
    }

    private fun clearInput() {
        currentInput = ""
        operand1 = null
        operator = null
        tvResult.text = ""
    }
}