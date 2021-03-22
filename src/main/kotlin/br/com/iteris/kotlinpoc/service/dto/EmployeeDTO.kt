package br.com.iteris.kotlinpoc.service.dto

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class EmployeeDTO(
        var id: Long? = null,

        @field:NotBlank
        val name: String = "",

        @field:Positive
        val salary: BigDecimal = BigDecimal.ZERO,

        @field:NotNull
        var departamentName: String = ""
) {
    fun completeDepartamentName(departamentDTO: DepartamentDTO) {
        this.departamentName = departamentDTO.name
    }
}

