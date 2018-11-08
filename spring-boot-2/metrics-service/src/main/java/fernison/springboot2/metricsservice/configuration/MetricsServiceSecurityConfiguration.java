package fernison.springboot2.metricsservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Configuración personalizada de Spring Security. Por defecto, con que el jar de Spring Security esté en el classpath se aplica la seguridad por defecto. 
 * @author fcabrero
 *
 */
@Configuration
@EnableWebSecurity
public class MetricsServiceSecurityConfiguration extends WebSecurityConfigurerAdapter { 
	
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//          .inMemoryAuthentication()
//          .withUser("user")
//            .password("{noop}password")
//            .roles("USER")
//            .and()
//          .withUser("admin")
//            .password("{noop}admin")
//            .roles("USER", "ADMIN");
//    }
 
//  @Autowired      // here is configuration related to spring boot basic authentication
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//      auth.inMemoryAuthentication()                   // for inMemory Authentication
//          .withUser("zone").password("{noop}contra").roles("USER")          // {noop} for plain text
//          .and()
//          .withUser("zone3").password("{noop}password").roles("USER");
//  }   

	@Bean
    public UserDetailsService userDetailsService() {
        // Get the user credentials from the console (or any other source):
        String username = "hans";
        String password = "hans";

        // Set the inMemoryAuthentication object with the given credentials:
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        String encodedPassword = passwordEncoder().encode(password);
        manager.createUser(User.withUsername(username).password(encodedPassword).roles("USER").build());
        return manager;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
        
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated();
        http.csrf().disable();  // Si no se pone esto no funciona ya que por defecto CSFR está habilitado
    }
}
