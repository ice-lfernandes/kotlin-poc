package br.com.iteris.kotlinpoc.service

import br.com.iteris.kotlinpoc.domain.entity.EletronicPoint
import br.com.iteris.kotlinpoc.domain.entity.Employee
import br.com.iteris.kotlinpoc.domain.entity.enum.TypePoint
import br.com.iteris.kotlinpoc.domain.repository.EletronicPointRepository
import br.com.iteris.kotlinpoc.exception.FatalException
import br.com.iteris.kotlinpoc.utils.ZONE_ID_DEFAULT
import br.com.iteris.kotlinpoc.utils.extensions.formatStringByPattern
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

    /**
     *
     * Realiza Controle de Batimento de Pontos dos Funcionários.
     * Caso 1: O funcionário não tenha batido ponto para a data atual do sistema, computar o ponto de entada.
     * Caso 2: O funcionário já tem uma batida de ponto para a data atual do sistema, computar o ponto de saída.
     * Caso 3: O funcionário já tem as batidas de entrada e saída para a data atual do sistema, retornar @throws FatalException.
     *
     * @param [employee] employee
     * @return [EletronicPoint] eletronicPoint
     * @throws [FatalException] exception
     */
    fun applyEletronicPointForEmployee(employee: Employee): EletronicPoint {
        val dateTime = LocalDateTime.now(ZONE_ID_DEFAULT).truncatedTo(ChronoUnit.DAYS)
        employee.id?.let {
            eletronicPointRepository.existsEletronicPointByEmployeeWithSameDate(it, dateTime.formatStringByPattern())
                    .ifEmpty {
                        return createEletronicPoint(employee, TypePoint.ENTRY, dateTime)
                    }.run {
                        this.filter { eletronicPoint -> eletronicPoint.typePoint == TypePoint.EXIT }
                                .ifEmpty {
                                    return createEletronicPoint(employee, TypePoint.EXIT, dateTime)
                                }
                        throw FatalException("O Funcionário: ${employee.name} já bateu os pontos de Entrada e Saída para a data: ${dateTime.formatStringByPattern("dd/MM/yyyy")}")
                    }
        }
        throw FatalException("O Funcionário: ${employee.name} não tem id válido, logo não está persistido")
    }

    private fun createEletronicPoint(employee: Employee, typePoint: TypePoint, dateTime: LocalDateTime): EletronicPoint {
        return EletronicPoint(employee = employee, typePoint = typePoint, dateTime = dateTime).run {
            val msg = "Batimento de ${typePoint.description} efetuado com sucesso"
            log.info("M=applyEletronicPointForEmployee, stage=apply-eletronic-point-success, employee=$employee, msg=$msg")
            eletronicPointRepository.save(this)
        }
    }

}