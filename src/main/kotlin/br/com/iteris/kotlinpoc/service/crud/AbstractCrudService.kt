package br.com.iteris.kotlinpoc.service.crud

import br.com.iteris.kotlinpoc.domain.entity.AbstractEntity
import br.com.iteris.kotlinpoc.exception.FatalException
import br.com.iteris.kotlinpoc.exception.NotFoundEntityException
import br.com.iteris.kotlinpoc.service.crud.dto.DTO
import org.slf4j.Logger
import org.springframework.data.repository.CrudRepository
import java.util.*
import kotlin.jvm.Throws

abstract class AbstractCrudService<T : AbstractEntity, V : DTO>(
        private var repository: CrudRepository<T, Long>
) {

    protected open lateinit var log: Logger

    protected abstract fun convertModelToDTO(entity: T): V

    protected abstract fun convertDTOToModel(dto: V): T

    fun findById(id: Long): Optional<V> {
        return repository.findById(id).map { entity ->
            log.info("method=findById, stage=entity-found, id=$id, entity=$entity")
            convertModelToDTO(entity)
        }
    }

    fun findAll(): List<V> = repository.findAll().map { entity ->
        convertModelToDTO(entity)
    }

    @Throws(FatalException::class)
    fun save(dtoForm: V): V {
        try {
            val entityPersisted = repository.save(convertDTOToModel(dtoForm))
            log.info("method=save, stage=entity-persisted-success, entity=$entityPersisted")
            return convertModelToDTO(entityPersisted)
        } catch (exception: Exception) {
            log.error("method=save, stage=error-save-entity, dto=$dtoForm, message=${exception.message}", exception)
            throw FatalException(message = "Error Save Entity", exception = exception)
        }
    }

    @Throws(FatalException::class, NotFoundEntityException::class)
    fun update(id: Long, dtoForm: V): V {
        return repository.findById(id).map { entity ->
            try {
                dtoForm.id = entity.id
                val entityPersisted = repository.save(convertDTOToModel(dtoForm))
                log.info("method=save, stage=entity-persisted-success, employ=$entityPersisted")
                convertModelToDTO(entityPersisted)
            } catch (exception: Exception) {
                log.error("method=save, stage=error-save-entity, dto=$dtoForm, message=${exception.message}", exception)
                throw FatalException(message = "Error Update Entity", exception = exception)
            }
        }.orElseThrow { throw NotFoundEntityException(message = "Entity Not Found") }
    }

    @Throws(FatalException::class, NotFoundEntityException::class)
    fun delete(id: Long) {
        val optionalEmployee = repository.findById(id)
        optionalEmployee.ifPresentOrElse({ entity ->
            try {
                repository.delete(entity)
            } catch (exception: Exception) {
                log.error("method=delete, stage=error-delete-entity, entity=$entity, message=${exception.message}", exception)
                throw FatalException(message = "Error Delete Entity", exception = exception)
            }
        }, {
            log.warn("method=delete, stage=not-found-entity, id=$id")
            throw NotFoundEntityException(message = "Entity Not Found")
        })
    }

}