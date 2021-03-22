package br.com.iteris.kotlinpoc.api.advice

import br.com.iteris.kotlinpoc.api.error.ErrorMessageVO
import br.com.iteris.kotlinpoc.exception.FatalException
import br.com.iteris.kotlinpoc.exception.NotFoundEntityException
import org.apache.tomcat.jni.Local
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler {

    @Autowired
    lateinit var messageResource: MessageSource

    @ExceptionHandler(FatalException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(exception: FatalException): ResponseEntity<ErrorMessageVO> {
        val errorMessageVO = ErrorMessageVO().apply {
            applyException(exception)
        }
        return ResponseEntity(errorMessageVO, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NotFoundEntityException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundEntityException(exception: NotFoundEntityException): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(exception: MethodArgumentNotValidException): ResponseEntity<List<ErrorMessageVO>> {
        val mutableListFieldErros = exception.bindingResult.fieldErrors
        val listMessageErrorVO = mutableListFieldErros.map { fieldError ->
            ErrorMessageVO().apply {
                field = fieldError.field
                messageError = "${fieldError.objectName} possui campos inválidos"
                messageCause = "$field ${messageResource.getMessage(fieldError, LocaleContextHolder.getLocale())}"
                exceptionClass = exception.javaClass.simpleName
            }
        }
        return ResponseEntity(listMessageErrorVO, HttpStatus.BAD_REQUEST)
    }

    private fun ErrorMessageVO.applyException(exception: Exception) {
        exception.message?.let { message ->
            messageError = message
        }
        exception.cause?.message?.let { message ->
            messageCause = message
        }
        exceptionClass = exception.javaClass.simpleName
    }
}