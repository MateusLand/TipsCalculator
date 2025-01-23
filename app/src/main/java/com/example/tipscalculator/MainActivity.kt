package com.example.tipscalculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipscalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text
            val numPeopleTemp = binding.tieNumPeople.text
            val percentageTemp = binding.tieTipPercentage.text

            if (totalTableTemp?.isEmpty() == true ||
                numPeopleTemp?.isEmpty() == true ||
                percentageTemp?.isEmpty() == true
                )
                    {
                Snackbar.make(binding.tieTotal, "Please fill all the fields", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                val totalTable = binding.tieTotal.text.toString().toFloat()
                val nPeople = numPeopleTemp.toString().toInt()
                val percentage = percentageTemp.toString().toInt()

                val totalTemp = totalTable / nPeople
                val tips = totalTemp * percentage / 100
                val totalWithTips = totalTemp + tips

                val intent = Intent(this, SummaryActivity::class.java)
                intent.apply {
                    intent.putExtra("totalTable", totalTable)
                    intent.putExtra("numPeople", nPeople)
                    intent.putExtra("percentage", percentage)
                    intent.putExtra("totalAmount", totalWithTips)

                }
                clean()
                startActivity(intent)
            }
        }

        binding.btnClear.setOnClickListener {
            clean()
        }
    }

    private fun clean() {
        binding.tieTotal.setText("")
        binding.tieNumPeople.setText("")
        binding.tieTipPercentage.setText("")
    }
}