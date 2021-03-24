package br.com.iteris.kotlinpoc.service

import br.com.iteris.kotlinpoc.domain.entity.EletronicPoint
import br.com.iteris.kotlinpoc.domain.entity.Employee
import br.com.iteris.kotlinpoc.domain.entity.enum.TypePoint
import br.com.iteris.kotlinpoc.domain.repository.EletronicPointRepository
import br.com.iteris.kotlinpoc.exception.FatalException
import br.com.iteris.kotlinpoc.utils.ZONE_ID_DEFAULT
import br.com.iteris.kotlinpoc.utils.extensions.formatStringByPattern
import br.com.iteris.kotlinpoc.utils.extensions.isTrue
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class EletronicPointService {

    private val log: Logger = LoggerFactory.getLogger(EletronicPointService::class.java)

    @Autowired
    private lateinit var eletronicPointRepository: EletronicPointRepository

    fun applyEletronicPointForEmployee(employee: Employee, typePoint: TypePoint): EletronicPoint {
        val dateTime = LocalDateTime.now(ZONE_ID_DEFAULT).truncatedTo(ChronoUnit.SECONDS)
        employee.id?.let {
            eletronicPointRepository.existsEletronicPointByEmployeeWithSameDateAndTypePoint(it, typePoint.ordinal, dateTime.formatStringByPattern())
                    .isTrue {
                        val msg = "Funcionário ja possui batimento de ${typePoint.description} para a data: $dateTime"
                        log.error("M=applyEletronicPointForEmployee, stage=apply-eletronic-point-error, batida=${typePoint.description}, employee=$employee, msg=$msg")
                        throw FatalException("O ponto de ${typePoint.description} do funcionário ${employee.name} não pode ser criado, pois já existe")
                    }
            return EletronicPoint(employee = employee, typePoint = typePoint, dateTime = dateTime).run {
                val msg = "Batimento de ${typePoint.description} efetuado com sucesso"
                log.info("M=applyEletronicPointForEmployee, stage=apply-eletronic-point-success, employee=$employee, msg=$msg")
                eletronicPointRepository.save(this)
            }
        }
        throw FatalException("O Funcionário não tem id válido, logo não está persistido")
    }

}