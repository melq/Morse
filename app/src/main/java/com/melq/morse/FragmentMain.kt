package com.melq.morse

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import com.melq.morse.databinding.FragmentMainBinding

class FragmentMain: Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel = ViewModelMain()

    /* 変換クラスのインスタンスの定義 */
    private val morse = Morse()

    /* 各要素の状態を保持するフィールド */
    private var isOutputVisible = true

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swMode.setOnCheckedChangeListener { _, isChecked  ->
            viewModel.setIsEncryptMode(isChecked)
            doTranslate(binding.swMode.isChecked, binding.etInputMain.text.toString())
        }

        binding.etInputMain.addTextChangedListener(object: CustomTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                doTranslate(binding.swMode.isChecked, s.toString())

                if (s?.isEmpty() == true) changeVisible(false)
                else changeVisible(true)
            }
        })

        binding.btClear.setOnClickListener { binding.etInputMain.setText("") }

        changeVisible(!binding.etInputMain.text.isNullOrEmpty())
    }

    fun doTranslate(encryptMode: Boolean, text: String) {
        viewModel.setOutPutText(
            if (encryptMode) morse.encryption(text)
            else morse.decryption(text)
        )
    }

    /* 出力エリアの表示切替 */
    fun changeVisible(isVisible: Boolean) {
        if (isOutputVisible != isVisible) {
            isOutputVisible = isVisible
            fadeAnimation(isVisible, binding.btClear)
            fadeAnimation(isVisible, binding.layoutOutput)
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