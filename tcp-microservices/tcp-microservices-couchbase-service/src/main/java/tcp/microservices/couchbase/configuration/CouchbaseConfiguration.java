package tcp.microservices.couchbase.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

// Esta clase no es necesaria. Con lo indicado en el fichero YAML es suficiente

//@Configuration
//@EnableCouchbaseRepositories(basePackages={"tcp.microservices.couchbase.persistence.repositories"})
public class CouchbaseConfiguration {
/*
	extends AbstractCouchbaseConfiguration {

 
    @Value("${spring.couchbase.bootstrap-hosts}")
    private List<String> servers;
    
    @Override
    protected List<String> getBootstrapHosts() {
        return servers;
    }

    @Override
    protected String getBucketName() {
        return "default";
    }
 
    @Override
    protected String getBucketPassword() {
        return "";
    }
    */
}