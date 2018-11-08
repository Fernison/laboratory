package fernison.springboot2.metricsservice.test.unit.service;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "fernison.springboot2.metricsservice.test.unit.service" })
class SpringContextAOP {
}

@Aspect
@Component
class MyAspect {
	@Before("execution(* fernison.springboot2.metricsservice.test.unit.service.HiByeService.*(..))")
	public void before(JoinPoint joinPoint) {
		System.out.print("Before ");
		System.out.print(joinPoint.getSignature().getName() + " called with ");
		System.out.println(Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(pointcut = "execution(* fernison.springboot2.metricsservice.test.unit.service.HiByeService.*(..))", returning = "result")
	public void after(JoinPoint joinPoint, Object result) {
		System.out.print("After ");
		System.out.print(joinPoint.getSignature().getName());
		System.out.println(" result is " + result);
	}

}

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringContextAOP.class)
public class AOPExampleTest {

	@Autowired
	private HiByeService service;

	@Test
	public void testSomething() {
		service.sayHi("in28Minutes");
		service.sayBye();
		service.returnSomething();
	}

}

@Component
class HiByeService {
	public void sayHi(String name) {
		System.out.println("Hi " + name);
	}

	public void sayBye() {
		System.out.println("Bye");
	}

	public String returnSomething() {
		return "Hi Bye";
	}
}