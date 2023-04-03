package gr.senik

import io.micrometer.observation.ObservationRegistry
import io.micrometer.observation.aop.ObservedAspect
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.CommonsRequestLoggingFilter
import org.springframework.web.filter.OncePerRequestFilter


@Configuration
class ObsConfig {

    // To have the @Observed support we need to register this aspect
    @Bean
    fun observedAspect(observationRegistry: ObservationRegistry?): ObservedAspect? {
        return ObservedAspect(observationRegistry!!)
    }

    // Logs all request
    @Bean
    fun logFilter(): CommonsRequestLoggingFilter {
        val filter = CustomRequestLoggingFilter()
        filter.setIncludeQueryString(true)
        filter.setIncludePayload(true)
        filter.setMaxPayloadLength(10000)
        filter.setIncludeHeaders(false)
        filter.setAfterMessagePrefix("REQUEST DATA: ")
        return filter
    }

}


/**
 * Sets user name in MDC context.
 */
@Component
class MDCLoggingFilter : OncePerRequestFilter() {

    companion object {
        private const val USER_ID_MDC_KEY = "userId"
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val authentication: Authentication? = SecurityContextHolder.getContext().authentication
            if (authentication != null) {
                val userName = authentication.name
                MDC.put(USER_ID_MDC_KEY, userName)
            }
            filterChain.doFilter(request, response)
        } finally {
            MDC.remove(USER_ID_MDC_KEY)
        }
    }
}

class CustomRequestLoggingFilter : CommonsRequestLoggingFilter() {
    override fun shouldLog(request: HttpServletRequest): Boolean {
        if (request.requestURI.contains("/actuator")) return false
        return super.shouldLog(request)
    }
}
