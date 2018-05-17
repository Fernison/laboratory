package tcp.microservices.data.jpa.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import tcp.microservices.data.jpa.configuration.PersistenceJPAConfiguration;
import tcp.microservices.data.jpa.models.Hobbie;
import tcp.microservices.data.jpa.models.Persona;
import tcp.microservices.data.jpa.models.Sexo;
import tcp.microservices.data.jpa.models.ShortPersona;
import tcp.microservices.data.jpa.repositories.HobbieRepository;
import tcp.microservices.data.jpa.repositories.PersonaRepository;
import tcp.microservices.data.jpa.repositories.SexoRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
//@Import(PersistenceJPAConfiguration.class)
@ContextConfiguration(classes = {PersistenceJPAConfiguration.class}) // Hace lo mismo que el anterior, solo que puede cargar una lista
public class RepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(RepositoryTest.class); 

	@Autowired
	private PersonaRepository persona_repository;
	@Autowired
	private SexoRepository sexo_repository;
	@Autowired
	private HobbieRepository hobbie_repository;
	
	//@Test // Si se comenta no se ejecuta el test
	public void save_sexo_shouldReturnSexo() {
        Sexo s= new Sexo();
        s.setDescripcion("HEMBRA");
        assertThat(sexo_repository.save(s).getId()).isGreaterThan(0);
	}

	@Test // Si se comenta no se ejecuta el test
	public void save_hobbie_shouldReturnHobbie() {
        Hobbie h= new Hobbie();
        h.setDescripcion("Dark Souls 2");
        Persona p=new Persona();
        p.setId(1);
        h.setPersona(p);
        assertThat(hobbie_repository.save(h).getId()).isGreaterThan(0);
	}
	
	@Test
	public void save_persona_shouldReturnPersona() {
        Persona p= new Persona(""+new Random().nextInt(),"bien","ok",new Date());
        Sexo s= new Sexo();
        s.setId(2);
        p.setSexo(s);
        Persona persona=persona_repository.save(p);
        log.info("persona.getId():"+persona.getId());
        assertThat(persona.getId()).isGreaterThan(0);
	}

	@Test
	public void save_nullPersona_shouldReturnNullException() {
        Persona p=null;
        // Esto es parecido a @Test(expected = ...Exception.class) sólo que da más información de la excepción //
        assertThatThrownBy(() -> persona_repository.save(p)).isInstanceOf(Exception.class);
	}
		
	@Test(expected = DataIntegrityViolationException.class)
	public void save_personaDuplicateDNI_shouldReturnDataIntegrityViolationException() {
        Persona p= new Persona("3","bien","ok",new Date());
        persona_repository.save(p);
	}

    @Test
    public void findAll_noParams_ShouldReturnPersonaList() {
        List<Persona> searchResults = persona_repository.findAll();
        searchResults.forEach(result -> log.info(""+result));
        assertThat(searchResults).isNotEmpty();
    }
    
    @Test
    public void findAll_bySexo_ShouldReturnPersonasList() {
    	Sexo s=new Sexo();
    	s.setId(1);
        List<Persona> searchResults = persona_repository.findAllBySexo(s);
        searchResults.forEach(result -> log.info(""+result));
        assertThat(searchResults).isNotEmpty();
    }
    
    @Test
    public void find_byId_ShouldReturnPersona() {
        Persona persona= persona_repository.findFullPersonaById_SQL(1);
        log.info("Persona SQL:"+persona);
        persona= persona_repository.findFullPersonaById_JPQL(1);
        log.info("Persona JPQL:"+persona);
        List<Object[]> list= persona_repository.findPartialPersonaById_SQL(1);
        list.forEach(e -> {
        	Arrays.asList(e).forEach(j -> log.info("List SQL:"+j));
        });
        list= persona_repository.findPartialPersonaById_JPQL(1);
        list.forEach(e -> {
        	Arrays.asList(e).forEach(j -> log.info("List JPQL:"+j));
        });
        assertThat(persona).isNotNull();
    }
    
    @Test
    public void find_byDni_ShouldReturnPersona() {
        Persona persona= persona_repository.findCustomized("407718794");
        log.info("Persona SQL:"+persona);
        assertThat(persona).isNotNull();
    }
    
    @Test
    public void find_byId_ShouldReturnShortPersona() {
        ShortPersona shortPersona= persona_repository.findPartialShortPersonaById_SQL(1);
        log.info("ShortPersona SQL:"+shortPersona);
        assertThat(shortPersona).isNotNull();
    }
}