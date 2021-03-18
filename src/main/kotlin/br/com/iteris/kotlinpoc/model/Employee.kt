package br.com.iteris.kotlinpoc.model

import java.math.BigDecimal

data class Employee(
        val id: String,
        val nome: String,
        val salario: BigDecimal
)