package gr.senik

import org.ff4j.FF4j
import org.ff4j.audit.repository.EventRepository
import org.ff4j.audit.repository.JdbcEventRepository
import org.ff4j.springjdbc.store.FeatureStoreSpringJdbc
import org.ff4j.springjdbc.store.PropertyStoreSpringJdbc
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


/**
 * Configure ff4j backed by databse stores.
 */
@Configuration
class FF4JConfig {


    @Bean
    fun fF4j(ds: DataSource): FF4j {
        val ff4j = FF4j()
        // Feature States in a RDBMS
        val featureStore = FeatureStoreSpringJdbc()
        featureStore.setDataSource(ds)
        ff4j.featureStore = featureStore
        // Properties in RDBMS
        val propertyStore = PropertyStoreSpringJdbc()
        propertyStore.setDataSource(ds)
        ff4j.propertiesStore = propertyStore
        // Audit in RDBMS
        // So far the implementation with SpringJDBC is not there, leverage on default JDBC
        val auditStore: EventRepository = JdbcEventRepository(ds)
        ff4j.eventRepository = auditStore

        return ff4j
    }
}
