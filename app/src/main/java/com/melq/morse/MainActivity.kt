package com.melq.morse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

}