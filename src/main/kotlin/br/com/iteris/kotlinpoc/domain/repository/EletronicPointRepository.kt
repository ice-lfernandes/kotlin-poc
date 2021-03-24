package br.com.iteris.kotlinpoc.domain.repository

import br.com.iteris.kotlinpoc.domain.entity.EletronicPoint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EletronicPointRepository : JpaRepository<EletronicPoint, Long> {

    @Query(value = "SELECT * FROM ELETRONIC_POINT ep WHERE ep.id_employee = :idEmployee and PARSEDATETIME(date_time, 'yyyy-MM-dd') = :dateTime",
            nativeQuery = true)
    fun existsEletronicPointByEmployeeWithSameDate(
            @Param("idEmployee") idEmployee: Long,
            @Param("dateTime") date: String): List<EletronicPoint>

}