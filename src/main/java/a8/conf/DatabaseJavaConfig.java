package a8.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value = "classpath:database/datasources.properties")
public class DatabaseJavaConfig {

	@Autowired
	Environment env;

	@Bean(name = "postgresqlDS")
	public DataSource getPostgresqlDS() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("postgresql_driverClassName"));
		dataSource.setUrl(env.getProperty("postgresql_url"));
		dataSource.setUsername(env.getProperty("postgresql_username"));
		dataSource.setPassword(env.getProperty("postgresql_password"));
		return dataSource;
	}
}
