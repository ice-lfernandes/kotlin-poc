package br.com.iteris.kotlinpoc.service

import br.com.iteris.kotlinpoc.domain.entity.Employee
import br.com.iteris.kotlinpoc.domain.repository.EmployeeRepository
import br.com.iteris.kotlinpoc.service.dto.EmployeeDTO
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeService {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Autowired
    lateinit var modelMapper: ModelMapper

    fun findById(id: Long): Optional<EmployeeDTO> =
            employeeRepository.findById(id).map { employee ->
                log.info("method=findById, stage=employee-found, id=$id, employee=$employee")
                modelMapper.map(employee, EmployeeDTO::class.java)
            }

    fun findAll(): List<Employee> = employeeRepository.findAll().toList()

    fun save(employeeDTOForm: Employee): Employee = employeeRepository.save(employeeDTOForm)

    fun delete(employeeDTOForm: Employee) = employeeRepository.delete(employeeDTOForm)
}