package tcp.microservices.check.interceptor.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import tcp.microservices.check.interceptor.ValidateHeaderInterceptor;
import tcp.microservices.check.interceptor.ValidateHeaderInterceptorAdapter;

@Configuration
@EnableWebMvc
public class InterceptorAutoConfiguration extends WebMvcConfigurerAdapter  {  
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new ValidateHeaderInterceptor());
	    registry.addInterceptor(new ValidateHeaderInterceptorAdapter());
	}
		 
}