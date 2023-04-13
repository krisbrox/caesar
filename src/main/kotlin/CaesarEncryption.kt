object CaesarEncryption {
    private fun Int.constrain(alphabet: String): Int {
        val constrained = this % alphabet.length

        return if (constrained < 0) {
            constrained + alphabet.length
        } else {
            constrained
        }
    }

    private fun Char.shift(alphabet: String, delta: Int): Char =
        when (val position = alphabet.indexOf(this)) {
            -1 -> this
            else -> alphabet[(position + delta).constrain(alphabet)]
        }

    private fun List<Char>.string() = this.fold("") { acc, char -> acc + char }

    fun String.alphabet() = this
        .toCharArray()
        .distinct()
        .filter { it.isLetter() }
        .sorted()
        .string()

    private fun shifted(text: String, delta: Int): String =
        text.map { it.shift(text.alphabet(), delta.constrain(text.alphabet())) }.string()

    private fun String.encryptionMap(delta: Int): Map<Char, Char> {
        val alphabet = this.alphabet()
        val shifted = shifted(alphabet, delta)
        return alphabet.mapIndexed { index, it -> it to shifted[index] }.toMap()
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
