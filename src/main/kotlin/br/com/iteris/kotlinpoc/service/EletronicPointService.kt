package br.com.iteris.kotlinpoc.service

import br.com.iteris.kotlinpoc.domain.entity.EletronicPoint
import br.com.iteris.kotlinpoc.domain.entity.Employee
import br.com.iteris.kotlinpoc.domain.entity.enum.TypePoint
import br.com.iteris.kotlinpoc.domain.repository.EletronicPointRepository
import br.com.iteris.kotlinpoc.exception.FatalException
import br.com.iteris.kotlinpoc.utils.ZONE_ID_DEFAULT
import br.com.iteris.kotlinpoc.utils.extensions.isTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.Throws

@Service
class EletronicPointService {

    @Autowired
    lateinit var eletronicPointRepository: EletronicPointRepository

    @Throws(FatalException::class)
    fun applyEletronicPointForEmployee(employee: Employee, typePoint: TypePoint): EletronicPoint {
        employee.id?.let {
            eletronicPointRepository.existsEletronicPointByEmployeeWithSameDateAndTypePoint(it, typePoint,
                    LocalDateTime.now(ZONE_ID_DEFAULT)
            ).isTrue {
                throw FatalException("O ponto não pode ser criado, pois já existe")
            }
            return EletronicPoint(employee = employee, typePoint = typePoint)
        }
        throw FatalException("O Funcionário não tem id válido, logo não está persistido")
    }

}