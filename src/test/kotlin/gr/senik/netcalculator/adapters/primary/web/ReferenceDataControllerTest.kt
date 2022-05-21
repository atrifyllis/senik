package gr.senik.netcalculator.adapters.primary.web

import com.fasterxml.jackson.databind.ObjectMapper
import gr.senik.netcalculator.IntegrationTestBase
import gr.senik.netcalculator.application.ports.`in`.web.dto.ReferenceDataDto
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@SpringBootTest
@AutoConfigureMockMvc
internal class ReferenceDataControllerTest : IntegrationTestBase() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    @WithMockUser(username = "admin", authorities = ["ADMIN", "USER"])
    fun `should retrieve reference data`() {
        val result = mockMvc.get("/reference-data") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content {
                    containsString("""{"eteaepClasses":[{"id":"14d0b02a-2898-4c7b-8519-3bf163f8f931","type":"FIRST"""")
                    containsString(""""efkaClasses":[{"id":"d55ec320-c0fe-4222-808e-3b52d9087061","type":"FIRST","mainPensionAmount":{"amount":155.00,"currencyCode":"EUR"}"""")
                }
            }
            .andDo { print() }
            .andReturn()

        val referenceDataDto = mapper.readValue(result.response.contentAsString, ReferenceDataDto::class.java)

        assertThat(referenceDataDto).isNotNull

        assertThat(referenceDataDto.efkaClasses).hasSize(7)
        assertThat(referenceDataDto.eteaepClasses).hasSize(3)
        assertThat(referenceDataDto.incomeTaxLevels).hasSize(5)
        assertThat(referenceDataDto.solidarityContributionTaxLevels).hasSize(7)
    }

    @Test
    fun `should throw unauthorised error when no user is logged in`() {
        mockMvc.get("/reference-data") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect {
                status { isUnauthorized() }
            }
            .andDo { print() }
    }
}
