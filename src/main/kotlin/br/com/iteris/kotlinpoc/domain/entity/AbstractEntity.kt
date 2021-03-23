package br.com.iteris.kotlinpoc.domain.entity

import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null
) {
    override fun equals(other: Any?): Boolean {
      return other is AbstractEntity && id == other.id
    }

    override fun hashCode(): Int = Objects.hashCode(id)

    protected fun generateFieldSuperClassToString() = "${this.javaClass.simpleName}(id = $id)".substringBeforeLast(")") + ","
}