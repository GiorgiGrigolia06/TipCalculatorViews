package com.example.tipcalculatorviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculatorviews.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()

        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_fifteen_percent -> 0.15
            R.id.option_eighteen_percent -> 0.18
            else -> 0.20
        }

        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked)
            tip = kotlin.math.ceil(tip)

        displayTip(tip)
    }

    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}

/**
 * Note: The name of the binding class is generated by converting the name of
 * the XML file to PascalCase and adding the word "Binding" to the end.
 * Similarly, the reference for each view is generated by removing
 * underscores and converting the view name to camelCase. For example,
 * in PascalCase activity_main.xml becomes ActivityMainBinding, and you can access @id/text_view as binding.textView.
 */
