package com.example.calculatorfr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculatorfr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding
    private lateinit var tvInput : TextView
    private var numbers = arrayListOf<Double>()
    private var operations = arrayListOf<String>()
    private var numberSting = ""
    private var lastNumeric = false
    private var lastDot = false
    private var lastOperation = false
    private var isTvInputEmpty = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvInput = binding.tvInput

        //numbers
        binding.btnZero.setOnClickListener{
            if (tvInput.text.isNotEmpty())
                onDigit(binding.btnZero)
        }
        binding.btnOne.setOnClickListener{
            onDigit(binding.btnOne)
        }
        binding.btnTwo.setOnClickListener{
            onDigit(binding.btnTwo)
        }
        binding.btnTree.setOnClickListener{
            onDigit(binding.btnTree)
        }
        binding.btnFour.setOnClickListener {
            onDigit(binding.btnFour)
        }
        binding.btnFive.setOnClickListener{
            onDigit(binding.btnFive)
        }
        binding.btnSix.setOnClickListener{
            onDigit(binding.btnSix)
        }
        binding.btnSeven.setOnClickListener{
            onDigit(binding.btnSeven)
        }
        binding.btnEight.setOnClickListener{
            onDigit(binding.btnEight)
        }
        binding.btnNine.setOnClickListener{
            onDigit(binding.btnNine)
        }

        //operations
        binding.btnPlus.setOnClickListener{
            onOperator(binding.btnPlus)
        }
        binding.btnMinus.setOnClickListener{
            onOperator(binding.btnMinus)
        }
        binding.btnMultiplication.setOnClickListener{
            onOperator(binding.btnMultiplication)
        }
        binding.btnDivision.setOnClickListener{
            onOperator(binding.btnDivision)
        }

        //calculation
        binding.btnEqual.setOnClickListener{
            onEqual()
        }

        //Extras
        binding.btnDecimal.setOnClickListener{
            onDecimalPoint()
        }
        binding.btnBracket.setOnClickListener{

        }
        binding.btnClear.setOnClickListener{
            onClear()
        }
        binding.btnDeleteLast.setOnClickListener{
            onDeleteLast()
        }
        binding.btnNegate.setOnClickListener{

        }

    }

    private fun onDeleteLast(){
        val input = tvInput.text.toString().substring(0 until tvInput.text.toString().length-1)
        tvInput.text = input
        if(lastDot && !lastNumeric)
            lastDot = false
        if (lastNumeric)
            lastNumeric = false
        if (lastOperation)
            lastOperation = false


        Log.e("lastNumeric", "$lastNumeric")
        Log.e("lastOperation","$lastOperation")
        Log.e("lastDot","$lastDot")
    }

    private fun onDigit(number : Button){
        tvInput.append(number.text)
        numberSting += number.text
        lastNumeric = true
        lastOperation = false
        isTvInputEmpty = false
    }

    private fun onClear(){
        tvInput.text = ""
        numberSting = ""
        numbers.clear()
        operations.clear()
        lastDot = false
        lastNumeric = false
        lastOperation = false
        isTvInputEmpty = true
    }

    private fun onDecimalPoint(){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            numberSting += "."
            lastDot = true
            lastNumeric = false
        }
    }

    private fun onEqual(){
        if (lastNumeric){
            numbers.add(numberSting.toDouble())
            var valueFinal : Double = 0.0

            try {
                if (operations.contains("*")){
                    for (index in operations.size-1 downTo 0){
                        if (operations[index] == "*"){

                            valueFinal = numbers[index] * numbers[index+1]
                            numbers[index] = valueFinal
                            numbers.removeAt(index+1)
                            operations.removeAt(index)
                            Log.e("numbers", numbers.toString())
                        }
                    }
                }

                while (operations.contains("/")){
                    for (index in 0 until operations.size){
                        if (operations[index] == "/"){
                            valueFinal = numbers[index] / numbers[index+1]
                            numbers[index] = valueFinal
                            if (index < operations.size)
                                numbers.removeAt(index+1)
                            operations.removeAt(index)
                            Log.e("numbers", numbers.toString())
                        }
                        break
                    }
                }
                valueFinal = 0.0
                numbers.forEach{
                    valueFinal += it
                    Log.e("valueFinal", valueFinal.toString())
                }

                val realFinalNum = removeZeroAfterDot(valueFinal.toString())
                tvInput.text = realFinalNum
                numberSting = realFinalNum
                numbers.clear()
                operations.clear()
                lastNumeric = true
                lastDot = false
                lastOperation = false

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun onOperator(operation: Button){
        if (isTvInputEmpty && operation.text == "-"){
            tvInput.append(operation.text)
            numberSting = "-"
            isTvInputEmpty = false
        }else{
            if(lastNumeric && !lastOperation){
                tvInput.append(operation.text)
                if (operation.text == "-" || operation.text == "+")
                    operations.add("+")
                else
                    operations.add(operation.text.toString())
                numbers.add(numberSting.toDouble())
                lastNumeric = false
                lastDot = false
                lastOperation = true
                numberSting = if (operation.text == "-")
                    "-"
                else
                    ""
            }
        }
        Log.e("numberString", numberSting)
        Log.e("numbers","$numbers")
        Log.e("operations","$operations")
    }

    private fun removeZeroAfterDot(result:String):String{
        return if (result.endsWith(".0"))
             result.substring(0,result.length-2)
        else
            result
    }

}