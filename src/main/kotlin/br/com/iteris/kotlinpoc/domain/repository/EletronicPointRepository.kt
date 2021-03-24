package br.com.iteris.kotlinpoc.domain.repository

import br.com.iteris.kotlinpoc.domain.entity.EletronicPoint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EletronicPointRepository : JpaRepository<EletronicPoint, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM ELETRONIC_POINT ep WHERE ep.id_employee = :idEmployee AND type_point = :typePoint and PARSEDATETIME(date_time, 'yyyy-MM-dd') = :dateTime",
            nativeQuery = true)
    fun existsEletronicPointByEmployeeWithSameDateAndTypePoint(
            @Param("idEmployee") idEmployee: Long,
            @Param("typePoint") typePoint: Int,
            @Param("dateTime") date: String): Boolean

}