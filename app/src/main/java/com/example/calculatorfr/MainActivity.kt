package com.example.calculatorfr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculatorfr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private var numbers = arrayListOf<Double>()
    private var operations = arrayListOf<Char>()
    private var numberSting = ""
    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onDigit(view:View){
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.text = ""
        lastDot = false
        lastNumeric = false
    }

    fun onDecimalPoint(view: View){
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun onEqual(view:View){
        if (lastNumeric){
            val tvInput = findViewById<TextView>(R.id.tvInput)
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try {

                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                val operations = "+-/*"

                for (operation in operations){
                    if (tvValue.contains(operation)){
                        val splitValue = tvValue.split(operation)

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        when(operation){
                            '-' -> tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                            '+' -> tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                            '/' -> tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                            '*' -> tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                        }
                        break
                    }
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun removeZeroAfterDot(result:String):String{
        return if (result.endsWith(".0"))
             result.substring(0,result.length-2)
        else
            result
    }

    private fun isOperatorAdded(value:String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("+") || value.contains("-") || value.contains("*")
        }
    }

    override fun onClick(view: View?) {
        val tvInput = binding.tvInput
        when(view?.id){
            binding.btnZero.id->{
                tvInput.append((view as Button).text)
                lastNumeric = true
            }
            binding.btnOne.id->{
                tvInput.append((view as Button).text)
                lastNumeric = true
            }
            binding.btnTwo.id->{
                tvInput.append((view as Button).text)
                lastNumeric = true
            }
            binding.btnTree.id->{
                tvInput.append((view as Button).text)
                lastNumeric = true
            }
            binding.btnFour.id->{
                tvInput.append((view as Button).text)
                lastNumeric = true
            }
            binding.btnFive.id->{
                tvInput.append((view as Button).text)
                lastNumeric = true
            }
            binding.btnSix.id->{
                tvInput.append((view as Button).text)
                lastNumeric = true
            }
            binding.btnSeven.id->{
                tvInput.append((view as Button).text)
                lastNumeric = true
            }
            binding.btnEight.id->{
            tvInput.append((view as Button).text)
            lastNumeric = true
            }
            binding.btnNine.id->{
                tvInput.append((view as Button).text)
                lastNumeric = true
            }
            binding.btnPlus.id->{
                if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
                    tvInput.append((view as Button).text)
                    operations.add('+')
                    lastNumeric = false
                    lastDot = false
                }
            }
            binding.btnMinus.id->{
                if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
                    tvInput.append((view as Button).text)
                    operations.add('-')
                    lastNumeric = false
                    lastDot = false
                }
            }
            binding.btnMultiplication.id->{
                if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
                    tvInput.append((view as Button).text)
                    operations.add('x')
                    lastNumeric = false
                    lastDot = false
                }
            }
            binding.btnDivision.id->{
                if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
                    tvInput.append((view as Button).text)
                    operations.add('/')
                    lastNumeric = false
                    lastDot = false
                }
            }
            binding.btnDecimal.id->{
                if(lastNumeric && !lastDot){
                    tvInput.append(".")
                    lastDot = true
                    lastNumeric = false
                }
            }
            binding.btnBracket.id->{

            }
            binding.btnClear.id->{
                tvInput.text = ""
                lastDot = false
                lastNumeric = false
            }
            binding.btnDeleteLast.id->{

            }
            binding.btnEqual.id->{
                if (lastNumeric){
                    var tvValue = tvInput.text.toString()
                    var prefix = ""

                    try {
                        if (tvValue.startsWith("-")){
                            prefix = "-"
                            tvValue = tvValue.substring(1)
                        }




                    }catch (e:ArithmeticException){
                        e.printStackTrace()
                    }
                }

            }
            binding.btnNegate.id->{
                tvInput.append((view as Button).text)
                lastNumeric = true
            }
        }
        Log.e("numbers","$numbers")
        Log.e("operations","$operations")
    }
}