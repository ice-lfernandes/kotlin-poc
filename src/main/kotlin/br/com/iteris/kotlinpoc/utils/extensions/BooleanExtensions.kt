package br.com.iteris.kotlinpoc.utils.extensions

fun Boolean.isTrue(block: (Boolean) -> Unit) : Boolean {
    if (this)
        block(this)
    return this
}

fun Boolean.isFalse(block: (Boolean) -> Unit) : Boolean {
    if (!this)
        block(this)
    return this
}