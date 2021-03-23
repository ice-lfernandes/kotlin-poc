package br.com.iteris.kotlinpoc.api.controller

import br.com.iteris.kotlinpoc.service.crud.DepartamentService
import br.com.iteris.kotlinpoc.service.crud.dto.DepartamentDTO
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

const val PATH_DEPARTAMENTS = "/departaments"

@RestController
@RequestMapping(PATH_DEPARTAMENTS)
class DepartamentController {

    private val log: Logger = LoggerFactory.getLogger(DepartamentController::class.java.name)

    @Autowired
    lateinit var departamentService: DepartamentService

    @Operation(summary = "Get Departament by Id")
    @ApiResponse(responseCode = "200", description = "Found Departament")
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDepartamentById(@PathVariable id: String): ResponseEntity<DepartamentDTO> {
        val idLong: Long? = convertStringToLong(id) { exception ->
            log.error(exception.message, exception)
        }

        idLong?.let {
            return departamentService.findById(it)
                    .map { departament -> ResponseEntity.ok(departament) }
                    .orElseGet { ResponseEntity.notFound().build() }
        }

        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @Operation(summary = "Get List of Departaments")
    @ApiResponse(responseCode = "200", description = "Found List of Departaments")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getEmployee(): ResponseEntity<List<DepartamentDTO>> = ResponseEntity.ok(departamentService.findAll())

    @Operation(summary = "Post Departament")
    @ApiResponse(responseCode = "201", description = "Departament Created")
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun postDepartament(
            @Valid @RequestBody departamentDTO: DepartamentDTO,
            uriComponentBuilder: UriComponentsBuilder,
    ): ResponseEntity<DepartamentDTO> {
        val departamentSaved = departamentService.save(departamentDTO)
        val uri = uriComponentBuilder.path("$PATH_DEPARTAMENTS/{id}").buildAndExpand(departamentSaved.id).toUri()
        return ResponseEntity.created(uri).body(Mapper.convert(departamentSaved))
    }

    @Operation(summary = "Put Departament")
    @ApiResponse(responseCode = "200", description = "Departament Updated")
    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun putEmployee(@PathVariable id: String, @Valid @RequestBody departamentDTO: DepartamentDTO): ResponseEntity<DepartamentDTO> {
        val idLong: Long? = convertStringToLong(id) { exception ->
            log.error(exception.message, exception)
        }

        idLong?.let {
            return ResponseEntity.ok(departamentService.update(it, departamentDTO))
        }

        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @Operation(summary = "Delete Departaments")
    @ApiResponse(responseCode = "200", description = "Departament Deleted")
    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: String): ResponseEntity<Any> {
        val idLong: Long? = convertStringToLong(id) { exception ->
            log.error(exception.message, exception)
        }

        idLong?.let {
            departamentService.delete(it)
            return ResponseEntity.ok().build()
        }

        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

}

