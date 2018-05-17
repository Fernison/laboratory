package tcp.microservices.couchbase;  

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.system.ApplicationPidFileWriter;

@SpringBootApplication

// Esto es necesario para que la conexión con Couchbase se haga correctamente ya que no se proporcionan ni driver ni
// más parámetros de configuración ni de Hibernate ni de nada
@EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class Application {  

	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }
    
} 
