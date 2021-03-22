package br.com.iteris.kotlinpoc.domain.entity

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(nullable = false)
        val name: String = "",

        @Column(nullable = false)
        val salary: BigDecimal = BigDecimal.ZERO,

        @Column(nullable = false)
        val dateCreated: LocalDateTime = LocalDateTime.now(),

        @JoinColumn(name = "id_departament")
        @ManyToOne(fetch = FetchType.LAZY)
        val departament: Departament
)