package br.com.iteris.kotlinpoc.api.controller

import br.com.iteris.kotlinpoc.domain.entity.Employee
import br.com.iteris.kotlinpoc.service.EmployeeService
import br.com.iteris.kotlinpoc.service.dto.EmployeeDTO
import br.com.iteris.kotlinpoc.utils.convertStringToLong
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

const val PATH = "/employees"

@RestController
@RequestMapping(PATH)
class EmployeeController {

    private val log: Logger = LoggerFactory.getLogger(EmployeeController::javaClass.name)

    @Autowired
    lateinit var employeeService: EmployeeService

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: String): ResponseEntity<EmployeeDTO> {
        val idLong: Long? = convertStringToLong(id) { exception ->
            log.error(exception.message, exception)
        }

        idLong?.let {
            return employeeService.findById(it)
                        .map { employee -> ResponseEntity.ok(employee) }
                        .orElseGet { ResponseEntity.notFound().build() }
        }

        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @GetMapping
    fun getEmployee(): ResponseEntity<List<Employee>> = ResponseEntity.ok(employeeService.findAll().toList())

    @PostMapping
    fun postEmployee(@RequestBody employeeDTOForm: Employee,  uriComponentBuilder: UriComponentsBuilder): ResponseEntity<Employee> {
        val employeeSaved = employeeService.save(employeeDTOForm)
        val uri = uriComponentBuilder.path("$PATH/{id}").buildAndExpand(employeeDTOForm.id).toUri()
        return ResponseEntity.created(uri).body(employeeSaved)
    }

    @PutMapping("/{id}")
    fun putEmployee(@PathVariable id: String, @RequestBody employeeDTOForm: Employee) {

    }

}

