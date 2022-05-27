package gr.senik

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


/**
 * The reason we added custom WebSecurityConfigurerAdapter is to allow openapi url to be accessed without authentication.
 * Check OktaOAuth2AutoConfig to see what we tried to replicate here.
 */
@Configuration
class WebConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {

        http {
            authorizeRequests {
                authorize("/v3/api-docs/**", permitAll)
                authorize(anyRequest, authenticated)
            }
            oauth2Client { }
            oauth2Login { }
            oauth2ResourceServer {
                jwt {}
            }
            // by default uses a Bean by the name of corsConfigurationSource
            cors {}
        }
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:4200")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*") // fixes: No 'Access-Control-Allow-Origin' header is present on the requested resource.
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
