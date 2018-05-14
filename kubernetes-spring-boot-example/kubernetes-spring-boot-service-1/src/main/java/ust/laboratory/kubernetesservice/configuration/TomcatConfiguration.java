package ust.laboratory.kubernetesservice.configuration;

import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfiguration {

	@Bean
	public EmbeddedServletContainerCustomizer tomcatCustomizer() {
		return (container) -> {
			if (container instanceof TomcatEmbeddedServletContainerFactory) {
				((TomcatEmbeddedServletContainerFactory) container).addConnectorCustomizers((connector) -> {
					connector.addUpgradeProtocol(new Http2Protocol());
				});			}
		};
	}

}
