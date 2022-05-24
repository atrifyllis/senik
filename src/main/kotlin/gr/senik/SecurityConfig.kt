package gr.senik

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer

/**
 * The reason we added custom WebSecurityConfigurerAdapter is to allow openapi url to be accessed without authentication.
 * Check OktaOAuth2AutoConfig to see what we tried to replicate here.
 */
@Configuration
class WebConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/v3/api-docs/**").permitAll()
            .anyRequest().authenticated()
            .and().oauth2Client()
            .and().oauth2Login()
            .and().oauth2ResourceServer { obj: OAuth2ResourceServerConfigurer<HttpSecurity?> -> obj.jwt() }
    }
}
