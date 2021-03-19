package br.com.iteris.kotlinpoc.model.repository

import br.com.iteris.kotlinpoc.model.entity.Employee
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : CrudRepository<Employee, Long>