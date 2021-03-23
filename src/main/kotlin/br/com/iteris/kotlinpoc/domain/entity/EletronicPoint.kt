package br.com.iteris.kotlinpoc.domain.entity

import br.com.iteris.kotlinpoc.domain.entity.enum.TypePoint
import br.com.iteris.kotlinpoc.utils.ZONE_ID_DEFAULT
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class EletronicPoint(
        id: Long? = null,

        @JoinColumn(name = "id_employee", nullable = false)
        @ManyToOne(fetch = FetchType.EAGER)
        val employee: Employee,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val typePoint: TypePoint,

        @Column(nullable = false)
        val dateTime: LocalDateTime = LocalDateTime.now(ZONE_ID_DEFAULT)
) : AbstractEntity(id)