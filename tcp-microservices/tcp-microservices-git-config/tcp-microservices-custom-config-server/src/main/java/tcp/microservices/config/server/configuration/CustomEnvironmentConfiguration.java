package tcp.microservices.config.server.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tcp.microservices.config.server.repositories.CustomEnvironmentRepository;

@Configuration
@Profile("custom")
public class CustomEnvironmentConfiguration {

    @Bean
    @ConditionalOnMissingBean(CustomEnvironmentRepository.class)
    public CustomEnvironmentRepository customEnvironmentRepository() {
        return new CustomEnvironmentRepository();
    }

}