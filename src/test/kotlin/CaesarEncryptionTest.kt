import CaesarEncryption.alphabet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CaesarEncryptionTest {

    @Test
    fun `can encrypt and decrypt simple english`() {
        val input = "The quick fox jumps over the lazy dog"
        val length = input.alphabet().length

        for (delta in -1 * length until length) {
            val encrypted = CaesarEncryption.encrypt(input, delta)
            val decrypted = CaesarEncryption.decrypt(encrypted, delta)
            assertThat(decrypted).isEqualTo(input)
        }
    }

    @Test
    fun `can encrypt and random text`() {
        val input = "And that brings us to the present day! If this is your first time stumbling" +
            " upon these two functions with similar names and functionality, hopefully this helps" +
            " explain the behavior you can expect from any recent Kotlin code, and how we got to" +
            " where we are now."
        val length = input.alphabet().length

        for (delta in -1 * length until length) {
            val encrypted = CaesarEncryption.encrypt(input, delta)
            val decrypted = CaesarEncryption.decrypt(encrypted, delta)
            assertThat(decrypted).isEqualTo(input)
        }
    }

    @Test
    fun `gives correct result in simple case`() {
        val input = "abcd"
        val delta = 1
        val encrypted = CaesarEncryption.encrypt(input, delta)
        val decrypted = CaesarEncryption.decrypt(encrypted, delta)

        assertThat(encrypted).isEqualTo("bcda")
        assertThat(decrypted).isEqualTo("abcd")
    }

    @Test
    fun `negative delta works as expected`() {
        val input = "abcd"
        val delta = -1
        val encrypted = CaesarEncryption.encrypt(input, delta)
        val decrypted = CaesarEncryption.decrypt(encrypted, delta)

        assertThat(encrypted).isEqualTo("dabc")
        assertThat(decrypted).isEqualTo("abcd")
    }

    @Test
    fun `special characters are ignored`() {
        val input = """ abcd[]†µAsdsd øøøø sdsd 3424-2423 $%&%&(/&)$#%" """
        val delta = 2
        val encrypted = CaesarEncryption.encrypt(input, delta)
        val decrypted = CaesarEncryption.decrypt(encrypted, delta)

        assertThat(encrypted).isNotEqualTo(input)
        assertThat(decrypted).isEqualTo(input)
    }

    @Test
    fun `does not give same output for reasonable input delta value`() {
        val input = "The quick fox jumps over the lazy dog"
        val delta = 4
        val encrypted = CaesarEncryption.encrypt(input, delta)
        val decrypted = CaesarEncryption.decrypt(encrypted, delta)

        assertThat(encrypted).isNotEqualTo(decrypted)
    }
}
