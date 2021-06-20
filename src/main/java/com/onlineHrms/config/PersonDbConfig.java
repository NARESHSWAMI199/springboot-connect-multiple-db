package com.onlineHrms.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( 
		entityManagerFactoryRef ="entityManagerFactory",
		basePackages = {"com.onlineHrms.person.repository"},transactionManagerRef = "transactionManager"
		)

public class PersonDbConfig {

	
//	create a DataSource
	@Primary
	@Bean(name="datasource")
	@ConfigurationProperties(prefix = "spring.person.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
//	Entity Manger Factory
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("datasource") DataSource dataSource ) {
		Map<String,Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto","update");
		properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		
		return builder.dataSource(dataSource).properties(properties).packages("com.onlineHrms.model.person").persistenceUnit("Person").build();
	}
	
	
// creating Transaction Manger
	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
 	
}
