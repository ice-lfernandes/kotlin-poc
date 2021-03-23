package br.com.iteris.kotlinpoc.service.crud

import br.com.iteris.kotlinpoc.domain.entity.Departament
import br.com.iteris.kotlinpoc.domain.repository.DepartamentRepository
import br.com.iteris.kotlinpoc.exception.FatalException
import br.com.iteris.kotlinpoc.exception.NotFoundEntityException
import br.com.iteris.kotlinpoc.service.crud.dto.DepartamentDTO
import br.com.iteris.kotlinpoc.utils.Mapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class DepartamentService(
        repository: DepartamentRepository<Departament, Long>
) : AbstractCrudService<Departament, DepartamentDTO>(repository) {

    override var log: Logger = LoggerFactory.getLogger(DepartamentService::class.java)

    override fun convertModelToDTO(entity: Departament): DepartamentDTO {
        return Mapper.convert(entity)
    }

    override fun convertDTOToModel(dto: DepartamentDTO): Departament {
        return Mapper.convert(dto)
    }

    @Throws(FatalException::class, NotFoundEntityException::class)
    fun update(id: Long, departamentoDTOForm: DepartamentDTO): DepartamentDTO {
        return repository.findById(id).map {
            try {
                departamentoDTOForm.id = it.id
                val departamentPersisted = repository.save(Mapper.convert(departamentoDTOForm))
                log.info("method=save, stage=departament-persisted-success, employ=$departamentPersisted")
                Mapper.convert<Departament, DepartamentDTO>(departamentPersisted)
            } catch (exception: Exception) {
                log.error("method=save, stage=error-save-departament, departamentDTO=$departamentoDTOForm, message=${exception.message}", exception)
                throw FatalException(message = "Error Update Departament", exception = exception)
            }
        }.orElseThrow { throw NotFoundEntityException(message = "Departament Not Found") }
    }

}