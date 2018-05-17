package tcp.microservices.data.jpa.repositories;
 
import org.springframework.data.jpa.repository.JpaRepository;

import tcp.microservices.data.jpa.models.Persona;
import tcp.microservices.data.jpa.models.Sexo;
 
public interface SexoRepository extends JpaRepository<Sexo,Integer> { 
	
}