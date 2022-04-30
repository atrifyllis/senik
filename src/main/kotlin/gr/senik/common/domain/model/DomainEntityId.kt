package gr.senik.common.domain.model

import org.jmolecules.ddd.types.Identifier
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

/**
 * Base class for all Aggregate Root Ids.
 */
@Embeddable
abstract class DomainEntityId(value: UUID?) : Identifier, Serializable {
    private var value: UUID? = null

    init {
        requireNotNull(value) { "Cannot make an ID from a null value." }
        this.value = value
    }

    override fun toString(): String {
        return if (value != null) value.toString() else "<Undefined from db yet. Repo save should be called first>"
    }

    override fun equals(other: Any?): Boolean {
        return if (this === other) {
            true
        } else if (other != null && this.javaClass == other.javaClass) {
            val entityID = other as DomainEntityId
            value == entityID.value
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(value)
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
