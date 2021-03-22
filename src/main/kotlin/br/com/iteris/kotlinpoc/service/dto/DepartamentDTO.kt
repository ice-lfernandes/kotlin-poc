package br.com.iteris.kotlinpoc.service.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class DepartamentDTO(
        var id: Long? = null,

        @field:NotBlank
        val name: String = "",

        @field:NotEmpty
        val listEmployeeDTO: List<EmployeeDTO> = listOf()
)