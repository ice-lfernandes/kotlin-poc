package br.com.iteris.kotlinpoc.tasks

import br.com.iteris.kotlinpoc.domain.entity.enum.TypePoint
import br.com.iteris.kotlinpoc.exception.FatalException
import br.com.iteris.kotlinpoc.service.EletronicPointService
import br.com.iteris.kotlinpoc.service.crud.EmployeeService
import br.com.iteris.kotlinpoc.utils.Mapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class ScheduledTask {

    private val log: Logger = LoggerFactory.getLogger(ScheduledTask::class.java)

    @Autowired
    private lateinit var eletronicPointService: EletronicPointService

    @Autowired
    private lateinit var employeeService: EmployeeService

    @Scheduled(fixedRate = 30000L, initialDelay = 5000L)
    fun computeEletronicPointRandomEmployees() {
        val listEmployee = employeeService.findAll()
        var index = Random().nextInt(listEmployee.size)
        val employee = listEmployee[index]

        try {
            eletronicPointService.applyEletronicPointForEmployee(
                    employee = Mapper.convert(employee)
            )
        } catch (exception: FatalException) {
            log.error(exception.message)
        }
    }
}