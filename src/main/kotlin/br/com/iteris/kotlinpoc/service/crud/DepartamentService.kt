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

    override fun convertModelToDTO(entity: Departament): DepartamentDTO = Mapper.convert(entity)

    override fun convertDTOToModel(dto: DepartamentDTO): Departament = Mapper.convert(dto)
}