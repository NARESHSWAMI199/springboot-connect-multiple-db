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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( 
		entityManagerFactoryRef ="blogEntityManagerFactory",
		basePackages = {"com.onlineHrms.blog.repository"},
		transactionManagerRef = "blogTransactionManager"
		
		)

public class BlogDbConfig {

	
//	create a DataSource
	@Bean(name="blogDatasource")
	@ConfigurationProperties(prefix = "spring.blog.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
//	Entity Manger Factory
	@Bean(name = "blogEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("blogDatasource") DataSource dataSource ) {
		Map<String,Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto","update");
		properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		
		return builder.dataSource(dataSource).properties(properties).packages("com.onlineHrms.model.blog").persistenceUnit("Blog").build();
	}
	
	
// creating Transaction Manger
	@Bean(name = "blogTransactionManager")
	public PlatformTransactionManager blogTransactionManager(
			@Qualifier("blogEntityManagerFactory") EntityManagerFactory blogEntityManagerFactory) {
		return new JpaTransactionManager(blogEntityManagerFactory);
	}
 	
}
