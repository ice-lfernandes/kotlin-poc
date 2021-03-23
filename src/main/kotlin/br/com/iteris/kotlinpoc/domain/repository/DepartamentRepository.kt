package br.com.iteris.kotlinpoc.domain.repository

import br.com.iteris.kotlinpoc.domain.entity.Departament
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DepartamentRepository : CrudRepository<Departament, Long> {

    fun findByName(name: String) : Optional<Departament>

}