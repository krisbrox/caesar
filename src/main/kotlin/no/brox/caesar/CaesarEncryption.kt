package no.brox.caesar

import no.brox.caesar.Utils.alphabet
import no.brox.caesar.Utils.string

object CaesarEncryption {

    private fun String.encryptionMap(delta: Int): Map<Char, Char> {
        val alphabet = this.alphabet()
        val shifted = alphabet.shifted(delta)
        return alphabet.chars.mapIndexed { index, it -> it to shifted.chars[index] }.toMap()
    }

    fun encrypt(text: String, delta: Int): String {
        val key = text.encryptionMap(delta)

        return text.map {
            key[it] ?: it
        }.string()
    }

    fun decrypt(encryptedText: String, delta: Int): String =
        encrypt(encryptedText, delta * -1)
}
