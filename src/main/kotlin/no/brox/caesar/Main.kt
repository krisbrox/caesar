package no.brox.caesar

import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}\n")

    runCatching {
        val delta = args[0].toInt()
        val inputFilename = args[1]
        val inputFile = File(inputFilename)

        val shouldDecrypt = args.contains("-d")
        val outputFilename = if (shouldDecrypt) {
            println("Decrypting")
            "decrypted_$inputFilename"
        } else {
            println("Encrypting")
            "encrypted_$inputFilename"
        }

        var output = ""
        for (line in inputFile.bufferedReader().readLines()) {
            output += if (shouldDecrypt) {
                val decryptedLine = CaesarEncryption.decrypt(line, delta)
                println(decryptedLine)
                decryptedLine
            } else {
                val encryptedLine = CaesarEncryption.encrypt(line, delta)
                println(encryptedLine)
                encryptedLine
            }
        }
        if (output.isNotEmpty()) {
            File(outputFilename).writeText(output)
        }

        exitProcess(0)
    }.onFailure {
        println("Usage: \"caesar \$delta \$input_text_file [optional] -d\"")
        exitProcess(1)
    }
}
