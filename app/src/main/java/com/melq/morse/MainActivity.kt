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
    private var encryptionMode = true
    private var vibration = true
    private var flash = true
    private var volume = true

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
                        if (encryptionMode) {
                            tvOutput.text = morse.encryption(s.toString())
                        } else {
                            tvOutput.text = morse.decryption(s.toString())
                        }
                    /* 処理 */
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

        menu?.findItem(R.id.option_vibration)?.setIcon(
                if (vibration) R.drawable.vibration_on
                else R.drawable.vibration_off)
        menu?.findItem(R.id.option_flash)?.setIcon(
                if (flash) R.drawable.flash_on
                else R.drawable.flash_off)
        menu?.findItem(R.id.option_volume)?.setIcon(
                if (volume) R.drawable.volume_on
                else R.drawable.volume_off)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_vibration ->
                vibration = !vibration
            R.id.option_flash ->
                flash = !flash
            R.id.option_volume ->
                volume = !volume
        }
        invalidateOptionsMenu()
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