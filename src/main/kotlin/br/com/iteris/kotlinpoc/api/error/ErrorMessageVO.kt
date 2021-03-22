package br.com.iteris.kotlinpoc.api.error

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ErrorMessageVO(
        var field: String = "",
        var code: String = "",
        var messageError: String = "",
        var messageCause: String = "",
        var exceptionClass: String = "",
)