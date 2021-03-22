package br.com.iteris.kotlinpoc.domain.entity

import javax.persistence.*

@Entity
data class Departament(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column(nullable = false)
        val name: String,

        @OneToMany(mappedBy = "departament", fetch = FetchType.LAZY, targetEntity = Employee::class)
        val listEmployee: List<Employee>
)