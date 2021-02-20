package com.melq.morse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.et_input_main)
        val textView: TextView = findViewById(R.id.tv_output_main)

        editText.addTextChangedListener(object: CustomTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                textView.text = s
            }
        })

        val btClear: ImageButton = findViewById(R.id.clear_text)
        val layoutOutput: View = findViewById(R.id.layout_output)
        var visibleOutput: Boolean = true
        btClear.setOnClickListener {
            visibleOutput = !visibleOutput
            fadeAnimation(visibleOutput, layoutOutput)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    fun fadeAnimation(fadeIn: Boolean, view: View) {
        val fadeAnimation: AlphaAnimation = if (fadeIn) {
            AlphaAnimation(0.0f, 1.0f)
        } else {
            AlphaAnimation(1.0f, 0.0f)
        }
        fadeAnimation.duration = 400
        fadeAnimation.fillAfter = true
        view.startAnimation(fadeAnimation)
    }

}