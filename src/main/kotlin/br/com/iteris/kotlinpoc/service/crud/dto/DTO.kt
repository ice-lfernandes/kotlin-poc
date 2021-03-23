package br.com.iteris.kotlinpoc.service.crud.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
abstract class DTO(var id: Long? = null)