package com.melq.morse

class Morse : Translator() {
    override fun encryption(input: String): String {
        if (input.isEmpty())
            return ""
        return "crypt: $input"
    }

    override fun decryption(input: String): String {
        if (input.isEmpty())
            return ""
        return "decrypt: $input"
    }
}

abstract class Translator {
    abstract fun encryption(input: String): String
    abstract fun decryption(input: String): String
}