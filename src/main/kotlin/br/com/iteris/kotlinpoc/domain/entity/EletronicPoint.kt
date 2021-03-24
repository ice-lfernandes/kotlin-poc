package br.com.iteris.kotlinpoc.domain.entity

import br.com.iteris.kotlinpoc.domain.entity.enum.TypePoint
import br.com.iteris.kotlinpoc.utils.ZONE_ID_DEFAULT
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.persistence.*

@Entity
class EletronicPoint(
        id: Long? = null,

        @JoinColumn(name = "id_employee", nullable = false)
        @ManyToOne(fetch = FetchType.EAGER)
        val employee: Employee,

        @Enumerated(EnumType.ORDINAL)
        @Column(nullable = false)
        val typePoint: TypePoint,

        @Type(type = "java.time.LocalDateTime")
        @Column(nullable = false)
        val dateTime: LocalDateTime = LocalDateTime.now(ZONE_ID_DEFAULT).truncatedTo(ChronoUnit.SECONDS)
) : AbstractEntity(id)