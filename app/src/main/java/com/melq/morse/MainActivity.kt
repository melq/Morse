package com.melq.morse

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    /* 要素の状態を示す変数 */
    private var vibration = true
    private var flash = false
    private var volume = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.linearLayout, FragmentMain())
                .commit()

//        /* 設定の読み込み */
//        val pref = getSharedPreferences("preference_root", Context.MODE_PRIVATE)
//        vibration = pref.getBoolean("vibration", true)
//        flash =     pref.getBoolean("flash", false)
//        volume =    pref.getBoolean("volume", false)
//
//        changeVisible(false)
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
        val pref = getSharedPreferences("preference_root", Context.MODE_PRIVATE)
        when (item.itemId) {
            R.id.option_vibration -> {
                vibration = !vibration
                pref.edit { putBoolean("vibration", vibration) }
            }
            R.id.option_flash -> {
                flash = !flash
                pref.edit { putBoolean("flash", flash) }
            }
            R.id.option_volume -> {
                volume = !volume
                pref.edit { putBoolean("volume", volume) }
            }
        }
        invalidateOptionsMenu() // 再描画
        /* 処理 */

        return true
    }
}