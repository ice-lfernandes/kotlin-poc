package br.com.iteris.kotlinpoc.controller

import br.com.iteris.kotlinpoc.model.entity.Employee
import br.com.iteris.kotlinpoc.model.repository.EmployeeRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.Objects.nonNull

const val PATH = "/employees"

@RestController
@RequestMapping(PATH)
class EmployeeController {

    val log: Logger = LoggerFactory.getLogger(EmployeeController().javaClass.simpleName)

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: String): ResponseEntity<Employee> {
        val idLong: Long? = try {
            id.toLong()
        } catch (exception: NumberFormatException) {
            log.error(exception.message, exception)
            null
        }
        return if (nonNull(idLong)) {
            idLong!!.let {
                employeeRepository.findById(it)
                        .map { employee -> ResponseEntity.ok(employee) }
                        .orElseGet { ResponseEntity.notFound().build() }
            }
        } else {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun getEmployee(): ResponseEntity<List<Employee>> = ResponseEntity.ok(employeeRepository.findAll().toList())

    @PostMapping
    fun postEmployee(@RequestBody employeeDTOForm: Employee, uriComponentBuilder: UriComponentsBuilder): ResponseEntity<Employee> {
        val uri = uriComponentBuilder.path("$PATH/{id}").buildAndExpand(employeeDTOForm.id).toUri()
        return ResponseEntity.created(uri).body(employeeDTOForm)
    }

    @PutMapping("/{id}")
    fun putEmployee(@PathVariable id: String, @RequestBody employeeDTOForm: Employee) {

    }

}

