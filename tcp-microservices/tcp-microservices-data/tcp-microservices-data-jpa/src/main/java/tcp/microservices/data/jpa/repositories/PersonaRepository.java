package tcp.microservices.data.jpa.repositories;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tcp.microservices.data.jpa.models.Persona;
import tcp.microservices.data.jpa.models.Sexo;
import tcp.microservices.data.jpa.models.ShortPersona;
 
public interface PersonaRepository extends JpaRepository<Persona,Integer> { 

	public List<Persona> findAllBySexo(Sexo sexo);
	
	// Ejemplo con query SQL estándar //
	// Es mejor preceder los parametros con @Param y hacerles referencia en la query // 
	@Query(value = "SELECT * FROM PERSONA WHERE ID = :id", nativeQuery = true)
	public Persona findFullPersonaById_SQL(@Param("id")int id);
	
	// Ejemplo con query JPQL //
	// Es mejor preceder los parametros con @Param y hacerles referencia en la query // 
	@Query("select p from Persona p where p.id=:id")
	public Persona findFullPersonaById_JPQL(@Param("id")int id);
	
	// Ejemplo con query SQL estándar //
	// Es mejor preceder los parametros con @Param y hacerles referencia en la query // 
	@Query(value = "SELECT ID, DNI, NOMBRE FROM PERSONA WHERE ID = :id", nativeQuery = true)
	public List<Object[]> findPartialPersonaById_SQL(@Param("id")int id);
	
	// Ejemplo con query JPQL estándar //
	// Este ejemplo mapea una consulta parcial en un entidad //
	// En la anotacion Query hay que declarar el objeto donde se mapea el resultado de la consulta //
	// Es mejor preceder los parametros con @Param y hacerles referencia en la query // 
	@Query("select new tcp.microservices.data.jpa.models.ShortPersona(p.id, p.dni, p.nombre ) from Persona p where p.id=:id")
	public ShortPersona findPartialShortPersonaById_SQL(@Param("id")int id);
	
	// Ejemplo con query JPQL //
	// Es mejor preceder los parametros con @Param y hacerles referencia en la query // 
	@Query("select p.id, p.nombre from Persona p where p.id=:id")
	public List<Object[]> findPartialPersonaById_JPQL(@Param("id")int id);
	
	// Este método se llama igual que la anotación NamedQuery de la clase Persona //
	// Se pueden añadir queries personalizadas y nombrarlas como métodos del repositorio //
	// Es mejor preceder los parametros con @Param y hacerles referencia en la query // 
	public Persona findCustomized(@Param("dni")String dni);
		
}