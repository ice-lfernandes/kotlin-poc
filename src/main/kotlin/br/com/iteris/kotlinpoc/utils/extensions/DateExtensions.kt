package br.com.iteris.kotlinpoc.utils.extensions

import br.com.iteris.kotlinpoc.utils.FORMAT_DATE_DEFAULT
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatStringByPattern(pattern: String = FORMAT_DATE_DEFAULT): String =
        this.format(DateTimeFormatter.ofPattern(pattern))