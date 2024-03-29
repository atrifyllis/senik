package gr.senik

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportRuntimeHints
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

fun main(args: Array<String>) {
    runApplication<SenikApplication>(*args)
}

@ImportRuntimeHints(MyRuntimeHints::class)
@SpringBootApplication(scanBasePackages = ["gr.alx", "gr.senik"]) // scan spring components from other jars
@EnableCaching
// only way I found to override FF4j FF4JOpenApiConfiguration
// excluding the autoconfiguration class did not work
@OpenAPIDefinition(
        info = Info(
                title = "SENIK",
                version = "1"
        )
)
@ConfigurationPropertiesScan
class SenikApplication


/**
 * Separate configuration class because when these anotations are set in SenikApplication class some test slices stop
 * working (like @JsonTest).
 */
@Configuration
@EntityScan(value = ["gr.alx", "gr.senik"]) // scan entities from other jars
@EnableJpaRepositories(basePackages = ["gr.alx", "gr.senik"]) // scan repositories from other jars
public class JpaConfig {

}
