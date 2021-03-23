package br.com.iteris.kotlinpoc.api.controller

import br.com.iteris.kotlinpoc.service.crud.EmployeeService
import br.com.iteris.kotlinpoc.service.crud.dto.EmployeeDTO
import br.com.iteris.kotlinpoc.utils.Mapper
import br.com.iteris.kotlinpoc.utils.convertStringToLong
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

const val PATH = "/employees"

@RestController
@RequestMapping(PATH)
class EmployeeController {

    private val log: Logger = LoggerFactory.getLogger(EmployeeController::class.java.name)

    @Autowired
    lateinit var employeeService: EmployeeService

    @Operation(summary = "Get Employee by Id")
    @ApiResponse(responseCode = "200", description = "Found Employee")
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

    @Operation(summary = "Get List of Employees")
    @ApiResponse(responseCode = "200", description = "Found List of Employees")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getEmployee(): ResponseEntity<List<EmployeeDTO>> = ResponseEntity.ok(employeeService.findAll())

    @Operation(summary = "Post Employee")
    @ApiResponse(responseCode = "201", description = "Employee Created")
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun postEmployee(
            @Valid @RequestBody employeeDTO: EmployeeDTO,
            uriComponentBuilder: UriComponentsBuilder,
    ): ResponseEntity<EmployeeDTO> {
        val employeeSaved = employeeService.save(employeeDTO)
        val uri = uriComponentBuilder.path("$PATH/{id}").buildAndExpand(employeeSaved.id).toUri()
        return ResponseEntity.created(uri).body(Mapper.convert(employeeSaved))
    }

    @Operation(summary = "Put Employee")
    @ApiResponse(responseCode = "200", description = "Employee Updated")
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

    @Operation(summary = "Delete Employee")
    @ApiResponse(responseCode = "200", description = "Employee Deleted")
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

