package com.wondercat.testapp.configuration;

import org.apache.commons.dbcp.BasicDataSource;
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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan("com.wondercat.testapp.service")
@EnableJpaRepositories(basePackages = "com.wondercat.testapp.repository", entityManagerFactoryRef = "entityManagerFactoryBean")
public class AppConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.wondercat.testapp.entity");
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource(){

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(environment.getProperty("spring.datasourse.url"));
        dataSource.setUsername(environment.getProperty("spring.datasourse.username"));
        dataSource.setPassword(environment.getProperty("spring.datasourse.password"));

        return dataSource;
    }

    // хз зачем
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){

        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

        return jpaTransactionManager;
    }

    @Bean
    public Properties additionalProperties(){

        Properties properties = new Properties();

        properties.setProperty("hibernate.ddl.auto",environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.current_session_context_class", environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", environment.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));

        return properties;
    }

}
