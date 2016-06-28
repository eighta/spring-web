package a8.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class RepositorioConf {

	@Bean
	public DataSource dataSourceDevInMemory() {
		String scriptsPath = "database/";
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
				.addScript(scriptsPath + "DDL.sql")
				.addScript(scriptsPath + "DML.sql").build();

		return db;
	}
	
}
