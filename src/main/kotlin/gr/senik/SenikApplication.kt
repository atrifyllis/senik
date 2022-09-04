package gr.senik

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.ff4j.spring.boot.autoconfigure.FF4JOpenApiConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [FF4JOpenApiConfiguration::class])
// only way I found to override FF4j FF4JOpenApiConfiguration
// excluding the auto-configuration class did not work
@OpenAPIDefinition(
    info = Info(
        title = "SENIK",
        version = "1"
    )
)
class SenikApplication

fun main(args: Array<String>) {
    runApplication<SenikApplication>(*args)
}
