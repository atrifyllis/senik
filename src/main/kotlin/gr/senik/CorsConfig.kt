package gr.senik

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cors")
class CorsConfig(val allowedOrigins: List<String>)
