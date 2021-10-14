package com.ChessOnline;

import com.ChessOnline.config.HibernateConfig;
import com.ChessOnline.config.MailConfig;
import org.h2.security.auth.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ConfigurationPropertiesScan("com.ChessOnline.config")
public class ChessOnlineApplication implements CommandLineRunner {

	private final DataSource dataSource;

	public ChessOnlineApplication(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args)  {
		SpringApplication.run(ChessOnlineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		loadData();
	}

	public void loadData() {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false,
				false, "UTF-8", new ClassPathResource("sql/data.sql"));
		resourceDatabasePopulator.execute(dataSource);

	}

}
