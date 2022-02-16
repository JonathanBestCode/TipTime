package com.example.tiptime

import android.icu.text.NumberFormat
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.tiptime.databinding.ActivityMainBinding
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.txtNum.text.toString()
        if(stringInTextField == ""){
            val toast = Toast.makeText(this, "Oh Noooo!!!", Toast.LENGTH_SHORT)
            toast.show()
        }else {
            val cost = stringInTextField.toDouble()
            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.rdbtn_20 -> 0.20
                R.id.rdbtn_18 -> 0.18
                else -> 0.15100
            }
            var tip = tipPercentage * cost
            val roundUp = binding.roundUpSwitch.isChecked
            if (roundUp) {
                tip = ceil(tip)
            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        }
    }
}

