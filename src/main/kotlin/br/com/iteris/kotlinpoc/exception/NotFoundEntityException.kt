package br.com.iteris.kotlinpoc.exception

class NotFoundEntityException(message: String, exception: Throwable) : Exception(message, exception) {

    constructor(message: String) : this(message, Exception())

}