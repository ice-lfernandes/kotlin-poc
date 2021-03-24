package br.com.iteris.kotlinpoc.utils

/**
 * Converte String para um tipo Long informando um bloco para tratar exceção
 *
 * @param string string que será convertida
 * @param blockException bloco de código que será executado em caso de exception
 * @return Long?
 */
fun convertStringToLong(string: String, blockException: (Exception) -> Unit): Long? = try {
    string.toLong()
} catch (exception: NumberFormatException) {
    blockException(exception)
    null
}