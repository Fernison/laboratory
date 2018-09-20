package tcp.microservices.war;  

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
    	SpringApplication.run(Application.class, args);
        
    }
    
//    public static void main(String[] args) throws Exception {
//        method1("a");
//        method1("a","b");
//        method1("a","b","c");
//    	SpringApplication.run(Application.class, args);
//        
//    }
//    
//    private static void method1(String... datos) {
//        for (int i = 0; i < datos.length; ++i) {
//        	System.out.println(datos[i]);
//        }
//    }

}
