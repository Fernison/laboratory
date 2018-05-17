package tcp.microservices.aws.lambda;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories("tcp.microservices.aws.lambda.db")
public class DatabaseConfiguration {

	Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    private static final String DB_URL = "spring.datasource.url";
    private static final String DB_USER = "spring.datasource.username";
    private static final String DB_PWD = "spring.datasource.password";
    
    private static final String DB_DIALECT = "spring.jpa.properties.hibernate.dialect";
    private static final String DB_DRIVER = "spring.datasource.driverClassName";
    private static final String DB_HIBERNATE_HBM2DDL_AUTO = "spring.jpa.hibernate.ddl-auto";
    private static final String DB_SHOW_SQL = "spring.jpa.show-sql";

    private static final String[] DB_ENTITIES = new String[] {
            "tcp.microservices.aws.lambda.db",
            "entities" };

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment env) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        log.info("DataSource: {}", dataSource);
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(DB_ENTITIES);

        Properties jpaProperties = new Properties();
        		
        jpaProperties.put("connection.driver_class", env.getRequiredProperty(DB_DRIVER));
        // Configures the used database dialect. This allows Hibernate to create SQL
        // that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty(DB_DIALECT));
        // Specifies the action that is invoked to the database when the Hibernate
        // SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty(DB_HIBERNATE_HBM2DDL_AUTO));
        // If the value of this property is true, Hibernate writes all SQL
        // statements to the console.
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty(DB_SHOW_SQL));

        log.info("jpaProperties: {}", jpaProperties);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
    
    @Bean
    public DataSource getDataSource(Environment env) {
    	JdbcDataSource ds = new JdbcDataSource();
    	ds.setURL(env.getRequiredProperty(DB_URL));
    	ds.setUser(env.getRequiredProperty(DB_USER));
    	ds.setPassword(env.getRequiredProperty(DB_PWD));
    	log.info("DataSource: {}", ds);
    	return ds;
    }

}
