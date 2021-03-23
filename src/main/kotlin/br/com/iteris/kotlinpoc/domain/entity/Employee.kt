package br.com.iteris.kotlinpoc.domain.entity

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Employee(
        id: Long? = null,

        @Column(nullable = false)
        val name: String = "",

        @Column(nullable = false)
        val salary: BigDecimal = BigDecimal.ZERO,

        @Column(nullable = false)
        val dateCreated: LocalDateTime = LocalDateTime.now(),

        @JoinColumn(name = "id_departament")
        @ManyToOne(fetch = FetchType.LAZY)
        val departament: Departament? = null
) : AbstractEntity(id) {

    override fun equals(other: Any?): Boolean {
        return super.equals(other) && other is Employee && name == other.name
    }

    override fun hashCode(): Int {
        return super.hashCode() + name.hashCode()
    }

    override fun toString(): String {
        return "${generateFieldSuperClassToString()} name='$name', salary=$salary, departament=$departament)"
    }

}