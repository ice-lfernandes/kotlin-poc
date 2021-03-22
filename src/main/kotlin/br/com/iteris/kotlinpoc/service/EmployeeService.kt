package br.com.iteris.kotlinpoc.service

import br.com.iteris.kotlinpoc.utils.Mapper
import br.com.iteris.kotlinpoc.domain.entity.Employee
import br.com.iteris.kotlinpoc.domain.repository.EmployeeRepository
import br.com.iteris.kotlinpoc.exception.FatalException
import br.com.iteris.kotlinpoc.exception.NotFoundEntityException
import br.com.iteris.kotlinpoc.service.dto.EmployeeDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws

@Service
class EmployeeService {

    private val log: Logger = LoggerFactory.getLogger(EmployeeService::javaClass.name)

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
    fun save(employeeDTOForm: EmployeeDTO): EmployeeDTO {
        try {
            val employeePersisted = employeeRepository.save(Mapper.convert(employeeDTOForm))
            log.info("method=save, stage=employee-persisted-success, employ=$employeePersisted")
            return Mapper.convert(employeePersisted)
        } catch (exception: Exception) {
            log.error("method=save, stage=error-save-employee, employeeDTO=$employeeDTOForm, message=${exception.message}", exception)
            throw FatalException(message = "Error Save Employee", exception = exception)
        }
    }

    @Throws(FatalException::class, NotFoundEntityException::class)
    fun update(id: Long, employeeDTOForm: EmployeeDTO): EmployeeDTO {
        return employeeRepository.findById(id).map {
            try {
                employeeDTOForm.id = it.id
                val employeePersisted = employeeRepository.save(Mapper.convert(employeeDTOForm))
                log.info("method=save, stage=employee-persisted-success, employ=$employeePersisted")
                Mapper.convert<Employee, EmployeeDTO>(employeePersisted)
            } catch (exception: Exception) {
                log.error("method=save, stage=error-save-employee, employeeDTO=$employeeDTOForm, message=${exception.message}", exception)
                throw FatalException(message = "Error Update Employee", exception = exception)
            }
        }.orElseThrow { throw NotFoundEntityException(message = "Employee Not Found") }
    }

    @Throws(FatalException::class, NotFoundEntityException::class)
    fun delete(id: Long) {
        val optionalEmployee = employeeRepository.findById(id)
        optionalEmployee.ifPresentOrElse({ employee ->
            try {
                employeeRepository.delete(employee)
            } catch (exception: Exception) {
                log.error("method=delete, stage=error-delete-employee, employeeDTO=$employee, message=${exception.message}", exception)
                throw FatalException(message = "Error Delete Employee", exception = exception)
            }
        }, {
            log.warn("method=delete, stage=not-found-employee, id=$id")
            throw NotFoundEntityException(message = "Employee Not Found")
        })
    }

}