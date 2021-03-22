package br.com.iteris.kotlinpoc.service.dto

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

data class EmployeeDTO(
        var id: Long? = null,
        @field:NotBlank var name: String = "",
        @field:PositiveOrZero var salary: BigDecimal = BigDecimal.ZERO
)