package no.brox.caesar

object Utils {
    fun List<Char>.string() = this.fold("") { acc, char -> acc + char }
    fun String.alphabet() = Alphabet(this)
}
