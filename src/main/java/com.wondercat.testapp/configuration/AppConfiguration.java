package com.wondercat.testapp.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.wondercat.testapp")
@EnableJpaRepositories(basePackages = "com.wondercat.testapp.repository",
        entityManagerFactoryRef = "entityManagerFactoryBean")
public class AppConfiguration {

    private final Environment environment;
    private final static Logger LOGGER = LogManager.getLogger(AppConfiguration.class);

    @Autowired
    public AppConfiguration(Environment environment) {
        LOGGER.info("Environment resource has set");
        this.environment = environment;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.wondercat.testapp.entity");
        em.setJpaVendorAdapter(vendorAdapter());
        em.setJpaProperties(additionalProperties());
        LOGGER.info("Entity manager factory bean has created");
        return em;
    }

    @Bean
    public HibernateJpaVendorAdapter vendorAdapter() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        LOGGER.info("Vendor adapter has created");
        return vendorAdapter;
    }

    /**
     * Need to configure connection to database.
     * This bean get data from application.properties file
     *
     * @return bean with information describes Spring
     * how to connect to database
     */
    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        LOGGER.info("Data source has created");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        LOGGER.info("Transaction manager has created");
        return jpaTransactionManager;
    }

    @Bean
    public Properties additionalProperties() {

        Properties properties = new Properties();

        properties.setProperty("hibernate.ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.current_session_context_class", environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", environment.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));

        return properties;
    }

}
