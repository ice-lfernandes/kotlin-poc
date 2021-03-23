package br.com.iteris.kotlinpoc.domain.repository

import br.com.iteris.kotlinpoc.domain.entity.Employee
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmployeeRepository : CrudRepository<Employee, Long> {

    fun findByName(name: String) : Optional<Employee>

}