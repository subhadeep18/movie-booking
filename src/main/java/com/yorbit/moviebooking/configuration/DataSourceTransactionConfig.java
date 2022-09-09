package com.yorbit.moviebooking.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="webEntityManagerFactory",
transactionManagerRef="webDbTransactionManager",
basePackages="com.yorbit.moviebooking.repository")
public class DataSourceTransactionConfig {
	
	public static final Logger LOGGER = Logger.getLogger(DataSourceTransactionConfig.class);
	
	private static final String DS_DRIVER_NAME = "spring.datasource.driverClassName";
	private static final String DS_URL = "spring.datasource.url";
	private static final String DS_USER_NAME = "spring.datasource.username";
	private static final String DS_PWD = "spring.datasource.password";
	private static final String DS_CONN_TIMEOUT = "spring.datasource.hikari.connection-timeout";
	private static final String DS_MAX_POOL_SIZE = "spring.datasource.hikari.maximum-pool-size";
	private static final String HB_HBM2DDL_AUTO = "spring.jpa.hibernate.ddl-auto";
	private static final String HB_DIALECT = "spring.jpa.properties.hibernate.dialect";
	private static final String HB_SHOW_SQL = "spring.jpa.show-sql";
	private static final String HB_CONN_CHARSET = "spring.jpa.properties.hibernate.connection.characterEncoding";
	private static final String HB_CONN_CHARENCODING = "spring.jpa.properties.hibernate.connection.CharSet";
	private static final String HB_CONN_USEUNICODE = "spring.jpa.properties.hibernate.connection.useUnicode";
	
	@Autowired
	private Environment env;
	
	@Bean(name = "webDataSource")
	public DataSource webDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(env.getProperty(DS_DRIVER_NAME));
		dataSource.setJdbcUrl(env.getProperty(DS_URL));
		dataSource.setUsername(env.getProperty(DS_USER_NAME));
		dataSource.setPassword(env.getProperty(DS_PWD));
		
		String connectionTimeout = env.getProperty(DS_CONN_TIMEOUT);
		if(connectionTimeout != null && connectionTimeout.length() > 0) {
			dataSource.setConnectionTimeout(Long.parseLong(connectionTimeout));
		}
		
		String maximumPoolSize = env.getProperty(DS_MAX_POOL_SIZE);
		if(maximumPoolSize != null && maximumPoolSize.length() > 0) {
			dataSource.setMaximumPoolSize(Integer.parseInt(maximumPoolSize));
		}
		return dataSource;
	}

	@Bean(name = "webEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean webEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(webDataSource());
		entityManager.setPackagesToScan("com.yorbit.moviebooking.model");
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(vendorAdapter);
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty(HB_HBM2DDL_AUTO));
		properties.put("hibernate.dialect", env.getProperty(HB_DIALECT));
		properties.put("hibernate.show_sql", env.getProperty(HB_SHOW_SQL));
		properties.put("spring.jpa.properties.hibernate.connection.characterEncoding", env.getProperty(HB_CONN_CHARENCODING));
		properties.put("spring.jpa.properties.hibernate.connection.CharSet", env.getProperty(HB_CONN_CHARSET));
		properties.put("spring.jpa.properties.hibernate.connection.useUnicode", env.getProperty(HB_CONN_USEUNICODE));
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}
	
	@Bean(name = "webDbTransactionManager")
	public PlatformTransactionManager webDbTransactionManager(
		@Qualifier("webEntityManagerFactory")EntityManagerFactory webEntityManagerFactory) {
		return new JpaTransactionManager(webEntityManagerFactory);
	}
	
}
