package tcp.playground.annotations;  

import java.lang.annotation.Annotation;
import java.util.Arrays;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

@SpringBootApplication
public class Application {  

	public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(Application.class);         
		application.addListeners(new ApplicationPidFileWriter());         
		application.run(args); 
//		System.out.println("Finding annotated classes using Spring:");
//        new Application().findAnnotatedClasses("tcp.playground");
	}
 
    public void findAnnotatedClasses(String scanPackage) {
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
        for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) {
        	printMetadata(beanDef);
            
        }
    }
 
    private ClassPathScanningCandidateComponentProvider createComponentScanner() {
        // Don't pull default filters (@Component, etc.):
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Findable.class));
        return provider;
    }
 
    private void printMetadata(BeanDefinition beanDef) {
        try {
            System.out.printf("getBeanClassName: %s\n",beanDef.getBeanClassName());
            Class<?> cl = Class.forName(beanDef.getBeanClassName());
            Findable findable = cl.getAnnotation(Findable.class);
            System.out.printf("Found class: %s, with meta name: %s%n\n", cl.getSimpleName(), findable.name());
            System.out.printf("Found class getSource: %s\n", beanDef.getSource());
            // Encontrar los métodos de esa clase con una determinada anotacion //
            Arrays.stream(cl.getMethods()).forEach(m -> {
            	Annotation annotation = m.getAnnotation(FindableMethod.class);
            	System.out.println(m);
            	if(annotation!=null) {
            		// Tiene la anotacion //
            		System.out.println(m);
            	}
            });
//            
//            Annotation findableMethodAnnot=AnnotationUtils.findAnnotation(cl, FindableMethod.class);
//            System.out.printf("findableMethodAnnot: %s\n", findableMethodAnnot);
//            
        } catch (Exception e) {
        	System.err.println("Got exception: " + e.getMessage());
        }
    }    
    
} 
