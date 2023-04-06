package gr.senik.netcalculator.domain.model

import com.fasterxml.jackson.databind.ObjectMapper
import gr.alx.common.domain.model.DomainEvent
import gr.senik.JacksonConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.context.annotation.Import


@JsonTest
@Import(value = [JacksonConfig::class])
class IncomeCalculatedTest(@Autowired val objectMapper: ObjectMapper) {
    @Test
    fun `should deserialize`() {
        val event: String = """
            {
              "type": "income-calculated",
              "income": {
                "amount": 60460.68,
                "currencyCode": "EUR"
              },
              "eventId": {
                "id": "d030521b-e012-4908-a9e2-c72e0f91a415"
              },
              "incomeId": {
                "id": "fcf62d96-681b-405d-b86c-220d08f96d30"
              },
              "occurredOn": [
                2023,
                3,
                13,
                21,
                20,
                13,
                342079300
              ],
              "individualId": {
                "id": "5feae1e8-86a5-4957-8be3-596a6ef29846"
              }
            }
            
         """.trimIndent()

        val incomeCalculated = objectMapper.readValue(event, DomainEvent::class.java)
        assertThat(incomeCalculated).isNotNull
    }
}
