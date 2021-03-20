package br.com.iteris.kotlinpoc.service.dto

import java.math.BigDecimal

data class EmployeeDTO(
        val name: String = "",
        val salary: BigDecimal = BigDecimal.ZERO
)