package com.melq.morse

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.melq.morse.databinding.FragmentMainBinding

class FragmentMain: Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel = ViewModelMain()

    private val morse = Morse()

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

        binding.etInputMain.addTextChangedListener(object: CustomTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.isEmpty() == true) {
                    /* 処理 */
                    changeVisible(false)
                } else {
                    if (binding.swMode.isChecked) {
                        viewModel.setOutPutText(morse.encryption(s.toString()))
                    } else {
                        viewModel.setOutPutText(morse.decryption(s.toString()))
                    }
                    /* 処理 */
                    changeVisible(true)
                }
            }
        })
    }

    /* 出力エリアの表示切替 */
    fun changeVisible(isVisible: Boolean) {
        if (binding.btClear.isVisible != isVisible) {
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