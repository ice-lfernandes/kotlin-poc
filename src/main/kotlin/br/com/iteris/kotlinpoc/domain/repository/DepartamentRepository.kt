package br.com.iteris.kotlinpoc.domain.repository

import br.com.iteris.kotlinpoc.domain.entity.Departament
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DepartamentRepository<T, U> : JpaRepository<Departament, Long> {

    fun findByName(name: String) : Optional<Departament>

}