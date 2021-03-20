package br.com.iteris.kotlinpoc.domain.repository

import br.com.iteris.kotlinpoc.domain.entity.Employee
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : CrudRepository<Employee, Long>