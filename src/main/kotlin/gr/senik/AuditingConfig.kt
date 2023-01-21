package gr.senik

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

/**
 * NOTE: if we move this class to child package it might not scan correctly
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class AuditingConfig {
    @Bean
    fun auditorProvider(): AuditorAware<String> {

        /*
      if you are using spring security, you can get the currently logged username with following code segment.
      Optional.of(SecurityContextHolder.getContext().authentication.name)
     */
        return AuditorAware {
            Optional.of(
                when {
                    SecurityContextHolder.getContext().authentication.name != null -> SecurityContextHolder.getContext().authentication.name
                    else -> "unauthenticated"
                }
            )
        }
    }
}
