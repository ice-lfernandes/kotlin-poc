package br.com.iteris.kotlinpoc.utils.extensions

/**
 * Adiciona função anonima para processar condicao se for TRUE.
 *
 * @return Boolean
 */
fun Boolean.isTrue(block: (Boolean) -> Unit) : Boolean {
    if (this)
        block(this)
    return this
}

/**
 * Adiciona função anonima para processar condicao se for FALSE.
 *
 * @return Boolean
 */
fun Boolean.isFalse(block: (Boolean) -> Unit) : Boolean {
    if (!this)
        block(this)
    return this
}