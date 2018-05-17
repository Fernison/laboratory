package tcp.microservices.couchbase.persistence.repositories;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import tcp.microservices.couchbase.persistence.entities.User;

@ViewIndexed(designDoc = "user", viewName = "all")
public interface UserRepository extends CouchbaseRepository<User, String> {

}
