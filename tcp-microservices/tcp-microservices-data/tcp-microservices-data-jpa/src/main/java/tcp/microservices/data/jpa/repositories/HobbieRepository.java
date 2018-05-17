package tcp.microservices.data.jpa.repositories;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tcp.microservices.data.jpa.models.Hobbie;
import tcp.microservices.data.jpa.models.Persona;
import tcp.microservices.data.jpa.models.Sexo;
 
public interface HobbieRepository extends JpaRepository<Hobbie,Integer> { 
	
}