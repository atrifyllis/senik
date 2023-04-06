package gr.senik

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import gr.senik.netcalculator.domain.model.IncomeCalculated

/**
 * All even types should be declared here, to be able to ser/deserialize them.
 * All even types should be declared here, to be able to ser/deserialize them.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
@JsonSubTypes(JsonSubTypes.Type(value = IncomeCalculated::class))
interface DomainEventMixIn {

}
