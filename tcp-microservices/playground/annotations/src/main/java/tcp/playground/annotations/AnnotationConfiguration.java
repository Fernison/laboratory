package tcp.playground.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfiguration implements ApplicationContextAware {

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

//        for (String beanName : applicationContext.getBeanDefinitionNames()) {
//            Object obj = applicationContext.getBean(beanName);
//            /*
//             * As you are using AOP check for AOP proxying. If you are proxying with Spring CGLIB (not via Spring AOP)
//             * Use org.springframework.cglib.proxy.Proxy#isProxyClass to detect proxy If you are proxying using JDK
//             * Proxy use java.lang.reflect.Proxy#isProxyClass
//             */
//            Class<?> objClz = obj.getClass();
//            if (org.springframework.aop.support.AopUtils.isAopProxy(obj)) {
//
//                objClz = org.springframework.aop.support.AopUtils.getTargetClass(obj);
//            }
//
//            for (Method m : objClz.getDeclaredMethods()) {
//                if (m.isAnnotationPresent(Versioned.class)) {
//                    //Should give you expected results
//                }
//            }
//        }
    	System.out.println("setApplicationContext: ");
    	for(String beanName: applicationContext.getBeanDefinitionNames()){
    		Object bean=applicationContext.getBean(beanName);    		
    		String className=AopUtils.getTargetClass(bean).getName();
    		Class<?> cl= null;
    		Method[] methods = null;
			try {
				cl = Class.forName(className);
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}    	
			Findable findable = cl.getAnnotation(Findable.class);
			if(findable!=null) {
				System.out.println("Findable "+cl);
				methods=cl.getDeclaredMethods();
		        for(Method m: methods){
		            if(m.isAnnotationPresent(FindableMethod.class)) {
		            	System.out.println("Metodo: "+m.getName());
		            	try {	            		
							System.out.println(m.invoke(bean, "args"));
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { //| InstantiationException e) {
							e.printStackTrace();
						}
		            }
		        }
			} else {
            	System.out.println("No findable");				
			}
    	}
    }	
}