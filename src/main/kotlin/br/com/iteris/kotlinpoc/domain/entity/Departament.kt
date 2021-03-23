package br.com.iteris.kotlinpoc.domain.entity

import javax.persistence.*

@Entity
class Departament(
        id: Long?,

        @Column(nullable = false)
        val name: String,

        @OneToMany(mappedBy = "departament", fetch = FetchType.LAZY, targetEntity = Employee::class)
        val listEmployee: List<Employee>
) : AbstractEntity(id) {

    override fun equals(other: Any?): Boolean {
        return super.equals(other) && other is Departament && name == other.name
    }

    override fun hashCode(): Int {
        return super.hashCode() + name.hashCode()
    }

    override fun toString(): String {
        return "${generateFieldSuperClassToString()} name='$name'"
    }
}