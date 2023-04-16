package no.brox.caesar

import no.brox.caesar.Utils.string

sealed class AbstractAlphabet {
    abstract val chars: List<Char>
    abstract val string: String

    fun size() = string.length

    fun shifted(delta: Int) = ShiftedAlphabet(this, delta)
}

class Alphabet(text: String) : AbstractAlphabet() {
    override lateinit var chars: List<Char>
    override lateinit var string: String

    init {
        chars = text.toCharArray()
            .distinct()
            .filter { it.isLetter() }
            .sorted()
        string = chars.string()
    }
}

class ShiftedAlphabet(
    baseAlphabet: AbstractAlphabet,
    delta: Int,
) : AbstractAlphabet() {
    override lateinit var chars: List<Char>
    override lateinit var string: String

    init {
        chars = shifted(baseAlphabet, delta)
        string = chars.string()
    }

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

    private fun shifted(alphabet: AbstractAlphabet, delta: Int): List<Char> =
        alphabet.chars.map { it.shift(alphabet.string, delta) }
}
