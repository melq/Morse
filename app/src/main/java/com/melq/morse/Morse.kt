package com.melq.morse

class Morse(var lang: Int) : Translator(lang) {

    override fun encryption(input: String): String {
        if (input.isEmpty())
            return ""

        return when (lang) {
            0 -> "crypt(jp): $input"
            1 -> "crypt(en): $input"
            else -> "crypt(jp): $input"
        }
    }

    override fun decryption(input: String): String {
        if (input.isEmpty())
            return ""

        return when (lang) {
            0 -> "decrypt(jp): $input"
            1 -> "decrypt(en): $input"
            else -> "decrypt(jp): $input"
        }
    }
}

abstract class Translator(lang: Int) {
    abstract fun encryption(input: String): String
    abstract fun decryption(input: String): String
}