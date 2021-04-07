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

class MainActivity : AppCompatActivity() {
    /* 要素の状態を示す変数 */
    private var isOutputVisible = true
    private var encryptionMode: Boolean = true

    /* viewやwidgetのフィールド */
    private lateinit var btClear: ImageButton
    private lateinit var layoutOutput: View
    private lateinit var swMode: SwitchCompat
    private lateinit var etInput: EditText
    private lateinit var tvOutput: TextView

    /* 変換クラスのインスタンスの定義 */
    private val morse = Morse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swMode = findViewById(R.id.sw_mode)
        swMode.isChecked = encryptionMode
        swMode.setOnCheckedChangeListener { _, isChecked ->
            encryptionMode = isChecked
            if (encryptionMode)
                tvOutput.text = morse.encryption(etInput.text.toString())
            else
                tvOutput.text = morse.decryption(etInput.text.toString())
            /* 処理 */
        }

        etInput = findViewById(R.id.et_input_main)
        tvOutput = findViewById(R.id.tv_output_main)
        etInput.addTextChangedListener(object: CustomTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                tvOutput.text = s
                if (s?.isEmpty() == true) {
                    /* 処理 */
                    changeVisible(false)
                } else {
                    /* 処理 */
                        if (encryptionMode) {
                            tvOutput.text = morse.encryption(s.toString())
                        } else {
                            tvOutput.text = morse.decryption(s.toString())
                        }
                    changeVisible(true)
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
        /* 処理 */
        return true
    }

    /* 出力エリアの表示切替 */
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