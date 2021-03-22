package br.com.iteris.kotlinpoc.api.controller

import br.com.iteris.kotlinpoc.service.EmployeeService
import br.com.iteris.kotlinpoc.service.dto.EmployeeDTO
import br.com.iteris.kotlinpoc.utils.Mapper
import br.com.iteris.kotlinpoc.utils.convertStringToLong
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

const val PATH = "/employees"

@RestController
@RequestMapping(PATH)
class EmployeeController {

    private val log: Logger = LoggerFactory.getLogger(EmployeeController::javaClass.name)

    @Autowired
    lateinit var employeeService: EmployeeService

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
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

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getEmployee(): ResponseEntity<List<EmployeeDTO>> = ResponseEntity.ok(employeeService.findAll())

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun postEmployee(
            @Valid @RequestBody employeeDTO: EmployeeDTO,
            uriComponentBuilder: UriComponentsBuilder,
    ): ResponseEntity<EmployeeDTO> {
        val employeeSaved = employeeService.save(employeeDTO)
        val uri = uriComponentBuilder.path("$PATH/{id}").buildAndExpand(employeeSaved.id).toUri()
        return ResponseEntity.created(uri).body(Mapper.convert(employeeSaved))
    }

    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun putEmployee(@PathVariable id: String, @Valid @RequestBody employeeDTO: EmployeeDTO): ResponseEntity<EmployeeDTO> {
        val idLong: Long? = convertStringToLong(id) { exception ->
            log.error(exception.message, exception)
        }

        idLong?.let {
            return ResponseEntity.ok(employeeService.update(it, employeeDTO))
        }

        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: String): ResponseEntity<Any> {
        val idLong: Long? = convertStringToLong(id) { exception ->
            log.error(exception.message, exception)
        }

        idLong?.let {
            employeeService.delete(it)
            return ResponseEntity.ok().build()
        }

        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

}

