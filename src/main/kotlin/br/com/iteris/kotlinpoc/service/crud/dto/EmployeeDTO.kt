package br.com.iteris.kotlinpoc.service.crud.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

@JsonInclude(JsonInclude.Include.NON_NULL)
data class EmployeeDTO(
        var id: Long? = null,

        @field:NotBlank
        val name: String = "",

        @field:Positive
        val salary: BigDecimal = BigDecimal.ZERO,

        var departamentName: String? = null
) {
    fun completeDepartamentName(departamentDTO: DepartamentDTO) {
        this.departamentName = departamentDTO.name
    }
}

