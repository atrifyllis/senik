package gr.fnik.common.domain.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Version

/**
 * Base class for all non-aggregate entities.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @Version
    @Column(name = "OPTLOCK")
    private val version: Long = 0

    @CreatedBy
    @Column(name = "created_by", updatable = false) // avoids unnecessary updates in db
    private var createdBy: String? = null

    @CreatedDate
    @Column(name = "created", updatable = false) // avoids unnecessary updates in db
    private var createdDate: LocalDateTime? = null

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private var lastModifiedBy: String? = null

    @LastModifiedDate
    @Column(name = "last_modified")
    private var lastModifiedDate: LocalDateTime? = null

    override fun toString(): String {
        return "BaseEntity(version=$version, created=$createdDate)"
    }

}
