package com.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        val edtDay = findViewById<TextView>(R.id.textView2)
        val txtmaxDegrees = findViewById<TextView>(R.id.textView3)
        val txtminDegrees = findViewById<TextView>(R.id.textView4)
        val txtweatherCondition = findViewById<TextView>(R.id.textView5)

        // Initialize them
        var i: Int = 0 // Stores number of entries in array
        var display: String = "" // Display blank first
        val maxEntries = 7 // Only allow 7 array entries

        var averageMin: Int = 0
        var averageMax: Int = 0
        var averageWeatherCondition: Int = 0
        var displayAverage: String = ""

        // Create arrays
        val day = Array(maxEntries) { "" }
        val minDegrees = Array(maxEntries) { 0 }
        val maxDegrees = Array(maxEntries) { 0 }
        val weatherCondition = Array(maxEntries) { "" }

        val btnInsert: Button = findViewById<Button>(R.id.btnInsert)
        val btnDetails: Button = findViewById<Button>(R.id.btnDetails)
        val btnClear: Button = findViewById<Button>(R.id.btnClear)

        btnInsert.setOnClickListener {
            // Check if there's enough entries
            if (i < maxEntries) {
                // Convert to display types
                day[i] = edtDay.text.toString()
                minDegrees[i] =
                    txtminDegrees.text.toString().toIntOrNull() ?: 0 // 0 is for error checking
                maxDegrees[i] =
                    txtmaxDegrees.text.toString().toIntOrNull() ?: 0 // 0 is for error checking
                weatherCondition[i] = txtweatherCondition.text.toString()
                i++ // Increment entry number

                Toast.makeText(this, "Entry added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Maximum entries reached", Toast.LENGTH_SHORT).show()
            }
        }

        // Clear the text views
        btnClear.setOnClickListener {
            edtDay.text = ""
            txtminDegrees.text = ""
            txtmaxDegrees.text = ""
            txtweatherCondition.text = ""
        }

        // Button to navigate to Details View
        btnDetails.setOnClickListener {
            display = ""

            for (counter in 0 until i) {
                display += "Day: ${day[counter]}\nMin Degrees: ${minDegrees[counter]}\nMax Degrees: ${maxDegrees[counter]}\nWeather Condition: ${weatherCondition[counter]}\n\n"
                averageMin += minDegrees[counter]
                averageMax += maxDegrees[counter]
                averageWeatherCondition += minDegrees[counter] + maxDegrees[counter]
            }


            averageWeatherCondition /= i
            averageMin /= i
            averageMax /= i

            displayAverage = "Min Degrees: ${averageMin}\nMax Degrees: ${averageMax}\nWeather Conditions: $averageWeatherCondition"
            display += displayAverage

            // Passes the data to DetailedViewActivity and start the activity
            val intent = Intent(this, DetailedViewActivity::class.java)
            intent.putExtra("DISPLAY_DATA", display)
            intent.putExtra("DISPLAY_DATA_AVG", displayAverage)
            startActivity(intent)
        }
    }
}


