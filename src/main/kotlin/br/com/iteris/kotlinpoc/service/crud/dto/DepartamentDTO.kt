package br.com.iteris.kotlinpoc.service.crud.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

class DepartamentDTO(
        id: Long? = null,
        @field:NotBlank val name: String = "",
        @field:NotEmpty val listEmployeeDTO: List<EmployeeDTO> = listOf()
) : DTO(id)