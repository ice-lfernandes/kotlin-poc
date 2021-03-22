package br.com.iteris.kotlinpoc.domain.entity

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Entity
data class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:NotBlank val name: String = "",
        @field:PositiveOrZero val salary: BigDecimal = BigDecimal.ZERO
)