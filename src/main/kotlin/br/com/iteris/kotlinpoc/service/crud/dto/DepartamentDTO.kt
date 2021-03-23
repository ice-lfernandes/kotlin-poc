package br.com.iteris.kotlinpoc.service.crud.dto

import com.fasterxml.jackson.annotation.JsonInclude
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DepartamentDTO(
        var id: Long? = null,

        @field:NotBlank
        val name: String = "",

        @field:NotEmpty
        val listEmployeeDTO: List<EmployeeDTO> = listOf()
)