package br.com.iteris.kotlinpoc.service.crud.dto

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

class EmployeeDTO(
        id: Long? = null,
        @field:NotBlank val name: String = "",
        @field:Positive val salary: BigDecimal = BigDecimal.ZERO,
        var departamentName: String? = null
) : DTO(id) {
    fun completeDepartamentName(departamentDTO: DepartamentDTO) {
        this.departamentName = departamentDTO.name
    }
}

