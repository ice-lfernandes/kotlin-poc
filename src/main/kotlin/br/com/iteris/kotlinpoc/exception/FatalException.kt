package br.com.iteris.kotlinpoc.exception

class FatalException(message: String, exception: Throwable) : Exception(message, exception) {

    constructor(message: String) : this(message, Exception())
}