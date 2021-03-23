package br.com.iteris.kotlinpoc.service.crud

import br.com.iteris.kotlinpoc.domain.entity.Departament
import br.com.iteris.kotlinpoc.domain.repository.DepartamentRepository
import br.com.iteris.kotlinpoc.exception.FatalException
import br.com.iteris.kotlinpoc.exception.NotFoundEntityException
import br.com.iteris.kotlinpoc.service.crud.dto.DepartamentDTO
import br.com.iteris.kotlinpoc.utils.Mapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class DepartamentService {

    private val log: Logger = LoggerFactory.getLogger(DepartamentService::class.java.name)

    @Autowired
    lateinit var departamentRepository: DepartamentRepository

    fun findById(id: Long): Optional<DepartamentDTO> =
            departamentRepository.findById(id).map { departament ->
                log.info("method=findById, stage=departament-found, id=$id, departament=$departament")
                Mapper.convert<Departament, DepartamentDTO>(departament)
            }

    fun findAll(): List<DepartamentDTO> =
            departamentRepository.findAll().map { departament ->
                Mapper.convert<Departament, DepartamentDTO>(departament)
            }

    @Throws(FatalException::class)
    fun save(departamentoDTOForm: DepartamentDTO): DepartamentDTO {
        try {
            val departamentPersisted = departamentRepository.save(Mapper.convert(departamentoDTOForm))
            log.info("method=save, stage=departament-persisted-success, departament=$departamentPersisted")
            return Mapper.convert<Departament, DepartamentDTO>(departamentPersisted)
        } catch (exception: Exception) {
            log.error("method=save, stage=error-save-departament, departamentDTO=$departamentoDTOForm, message=${exception.message}", exception)
            throw FatalException(message = "Error Save Departament", exception = exception)
        }
    }

    @Throws(FatalException::class, NotFoundEntityException::class)
    fun update(id: Long, departamentoDTOForm: DepartamentDTO): DepartamentDTO {
        return departamentRepository.findById(id).map {
            try {
                departamentoDTOForm.id = it.id
                val departamentPersisted = departamentRepository.save(Mapper.convert(departamentoDTOForm))
                log.info("method=save, stage=departament-persisted-success, employ=$departamentPersisted")
                Mapper.convert<Departament, DepartamentDTO>(departamentPersisted)
            } catch (exception: Exception) {
                log.error("method=save, stage=error-save-departament, departamentDTO=$departamentoDTOForm, message=${exception.message}", exception)
                throw FatalException(message = "Error Update Departament", exception = exception)
            }
        }.orElseThrow { throw NotFoundEntityException(message = "Departament Not Found") }
    }

    @Throws(FatalException::class, NotFoundEntityException::class)
    fun delete(id: Long) {
        val optionalEmployee = departamentRepository.findById(id)
        optionalEmployee.ifPresentOrElse({ departament ->
            try {
                departamentRepository.delete(departament)
            } catch (exception: Exception) {
                log.error("method=delete, stage=error-delete-departament, departamentDTO=$departament, message=${exception.message}", exception)
                throw FatalException(message = "Error Delete Departament", exception = exception)
            }
        }, {
            log.warn("method=delete, stage=not-found-departament, id=$id")
            throw NotFoundEntityException(message = "Departament Not Found")
        })
    }
}