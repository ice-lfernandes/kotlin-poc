package br.com.iteris.kotlinpoc.api.advice

import br.com.iteris.kotlinpoc.api.error.ErrorMessageVO
import br.com.iteris.kotlinpoc.exception.FatalException
import br.com.iteris.kotlinpoc.exception.NotFoundEntityException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler {

    @Autowired
    lateinit var messageResource: MessageSource

    @ExceptionHandler(FatalException::class)
    fun handleException(exception: FatalException): ResponseEntity<ErrorMessageVO> {
        val errorMessageVO = ErrorMessageVO().apply {
            applyException(exception)
        }
        return ResponseEntity(errorMessageVO, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NotFoundEntityException::class)
    fun handleNotFoundEntityException(exception: NotFoundEntityException): ResponseEntity<ErrorMessageVO> {
        val errorMessageVO = ErrorMessageVO().apply {
            applyException(exception)
        }
        return ResponseEntity(errorMessageVO, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(exception: MethodArgumentNotValidException): ResponseEntity<List<ErrorMessageVO>> {
        val mutableListFieldErros = exception.bindingResult.fieldErrors
        val listMessageErrorVO = mutableListFieldErros.map { fieldError ->
            ErrorMessageVO().apply {
                field = fieldError.field
                messageError = "${fieldError.objectName} possui campos inválidos"
                messageCause = messageResource.getMessage(fieldError, LocaleContextHolder.getLocale())
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