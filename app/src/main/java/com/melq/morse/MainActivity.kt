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
    /*要素の表示非表示を示す変数*/
    var isOutputVisible = false

    /*viewやwidgetのフィールド*/
    private lateinit var etInput: EditText
    private lateinit var tvOutput: TextView
    private lateinit var btClear: ImageButton
    private lateinit var layoutOutput: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etInput = findViewById(R.id.et_input_main)
        tvOutput = findViewById(R.id.tv_output_main)

        etInput.addTextChangedListener(object: CustomTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                tvOutput.text = s
                if (s?.isNotEmpty() == true) {
                    changeVisible(true)
                } else {
                    changeVisible(false)
                }
            }
        })

        btClear = findViewById(R.id.clear_text)
        layoutOutput = findViewById(R.id.layout_output)
        btClear.setOnClickListener {
            etInput.setText("")
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    fun changeVisible(isVisible: Boolean) {
        if (isOutputVisible != isVisible) {
            isOutputVisible = isVisible
            fadeAnimation(isVisible, btClear)
            fadeAnimation(isVisible, layoutOutput)
        }
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