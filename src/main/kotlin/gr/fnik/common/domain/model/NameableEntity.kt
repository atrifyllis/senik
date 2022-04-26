package gr.fnik.common.domain.model

import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 * Represents an entity with a name.
 */
@MappedSuperclass
abstract class NameableEntity(

    @Column(name = "name")
    var name: String? = null

) : BaseEntity()
