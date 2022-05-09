package com.ws.CRUDtest;

import java.sql.Connection;
import java.util.stream.Stream;

import javax.sql.DataSource;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.DatabaseStartupValidator;

@SpringBootApplication
public class CRUDtestApplication {

	public static void main(String[] args) {

		SpringApplication.run(CRUDtestApplication.class, args);
	}
	@Bean
	public static BeanFactoryPostProcessor dependsOnPostProcessor() {
		return bf -> {
			// Let beans that need the database depend on the DatabaseStartupValidator
			// like the JPA EntityManagerFactory or Flyway

			String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
			Stream.of(jpa)
			.map(bf::getBeanDefinition)
			.forEach(it -> it.setDependsOn("databaseStartupValidator"));	        
		};
	}
	@Bean
	public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
		DatabaseStartupValidator dsv = new DatabaseStartupValidator();
		dsv.setDataSource(dataSource);
		dsv.setTimeout(60);
		dsv.setInterval(7);
		dsv.setValidationQuery(DatabaseDriver.MYSQL.getValidationQuery());
		return dsv;
	}
}
