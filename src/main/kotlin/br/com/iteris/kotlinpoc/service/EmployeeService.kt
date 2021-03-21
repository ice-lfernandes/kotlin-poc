package br.com.iteris.kotlinpoc.service

import br.com.iteris.kotlinpoc.utils.Mapper
import br.com.iteris.kotlinpoc.domain.entity.Employee
import br.com.iteris.kotlinpoc.domain.repository.EmployeeRepository
import br.com.iteris.kotlinpoc.service.dto.EmployeeDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws

@Service
class EmployeeService {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    fun findById(id: Long): Optional<EmployeeDTO> =
            employeeRepository.findById(id).map { employee ->
                log.info("method=findById, stage=employee-found, id=$id, employee=$employee")
                Mapper.convert<Employee, EmployeeDTO>(employee)
            }

    fun findAll(): List<EmployeeDTO> =
            employeeRepository.findAll().map { employee ->
                Mapper.convert<Employee, EmployeeDTO>(employee)
            }

    @Throws(Exception::class)
    fun save(employeeDTOForm: EmployeeDTO): Employee {
        try {
            return employeeRepository.save(Mapper.convert(employeeDTOForm))
        } catch (exception: Exception) {
            log.error("method=save, stage=error-save-employee, employeeDTO=$employeeDTOForm, message=${exception.message}", exception)
            throw exception
        }
    }

    @Throws(Exception::class)
    fun delete(employeeDTOForm: Employee) = try {
        employeeRepository.delete(employeeDTOForm)
    } catch (exception: Exception) {
        log.error("method=delete, stage=error-delete-employee, employeeDTO=$employeeDTOForm, message=${exception.message}", exception)
        throw exception
    }
}