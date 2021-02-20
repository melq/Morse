package com.melq.morse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    /*要素の状態を示す変数*/
    private var isOutputVisible = true
    private var translateMode: Boolean = false

    /*viewやwidgetのフィールド*/
    private lateinit var btClear: ImageButton
    private lateinit var layoutOutput: View
    private lateinit var swMode: SwitchCompat
    private lateinit var etInput: EditText
    private lateinit var tvOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swMode = findViewById(R.id.sw_mode)
        swMode.isChecked = translateMode
        swMode.setOnCheckedChangeListener { _, isChecked ->
            translateMode = isChecked
        }

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

        changeVisible(false)
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

    private fun fadeAnimation(fadeIn: Boolean, view: View) {
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