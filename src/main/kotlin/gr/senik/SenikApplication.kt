package gr.senik

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportRuntimeHints

fun main(args: Array<String>) {
    runApplication<SenikApplication>(*args)
}

@ImportRuntimeHints(MyRuntimeHints::class)
@SpringBootApplication
// only way I found to override FF4j FF4JOpenApiConfiguration
// excluding the auto-configuration class did not work
@OpenAPIDefinition(
    info = Info(
        title = "SENIK",
        version = "1"
    )
)
@ConfigurationPropertiesScan
class SenikApplication
