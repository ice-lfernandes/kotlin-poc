package br.com.iteris.kotlinpoc.service.dto

import java.math.BigDecimal

data class EmployeeDTO(
        val id: Long = 0L,
        val name: String = "",
        val salary: BigDecimal = BigDecimal.ZERO
)