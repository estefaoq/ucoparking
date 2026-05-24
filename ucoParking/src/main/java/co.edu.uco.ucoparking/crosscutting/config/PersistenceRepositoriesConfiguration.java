package co.edu.uco.ucoparking.crosscutting.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import co.edu.uco.ucoparking.infraestructure.persistence.repository.r2dbc.ParkingSpaceRepository;
import javax.sql.DataSource;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@EntityScan(basePackages = "co.edu.uco.ucoparking.infraestructure.persistence")
@EnableJpaRepositories(basePackages = "co.edu.uco.ucoparking.infraestructure.persistence.repository.sql")
@EnableR2dbcRepositories(basePackageClasses = ParkingSpaceRepository.class)
public class PersistenceRepositoriesConfiguration {

    @Bean
    public DataSource jdbcDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }
}
