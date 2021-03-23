package br.com.iteris.kotlinpoc.service.crud

import br.com.iteris.kotlinpoc.domain.entity.Employee
import br.com.iteris.kotlinpoc.domain.repository.EmployeeRepository
import br.com.iteris.kotlinpoc.service.crud.dto.EmployeeDTO
import br.com.iteris.kotlinpoc.utils.Mapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EmployeeService(
        repository: EmployeeRepository<Employee, Long>
) : AbstractCrudService<Employee, EmployeeDTO>(repository) {

    override var log: Logger = LoggerFactory.getLogger(EmployeeService::class.java.name)

    override fun convertModelToDTO(entity: Employee): EmployeeDTO = Mapper.convert(entity)

    override fun convertDTOToModel(dto: EmployeeDTO): Employee = Mapper.convert(dto)

}