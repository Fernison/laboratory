package tcp.microservices.data.jpa.test;

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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import tcp.microservices.data.jpa.models.Persona;
import tcp.microservices.data.jpa.repositories.PersonaRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Se usa una BBDD en memoria para hacer pruebas sin usar la BBDD real
 * @author fcabrero
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestJPAConfiguration.class}) // Hace lo mismo que el anterior, solo que puede cargar una lista
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
@DatabaseSetup("persona-entries.xml")
public class DBUnitRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(DBUnitRepositoryTest.class); 

	@Autowired
	private PersonaRepository repository;
	
    @Test
    public void findAll_NoParams_ShouldReturnPersonaEntryList() {
        List<Persona> searchResults = repository.findAll();
        searchResults.forEach(result -> log.info("Resultado de la busqueda:"+result));
        assertThat(searchResults).isNotEmpty();
    }
     
	@Test
	public void savePersonaOK() {
        Persona p= new Persona(""+new Random().nextInt(),"bien","ok",new Date());
        Persona persona=repository.save(p);
        log.info("persona.getId():"+persona.getId());
        assertThat(persona.getId()).isGreaterThan(0);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void savePersonaKO_duplicate_dni() {
        Persona p= new Persona("dni_3","bien","ok",new Date());
        repository.save(p);
	}
	
//    @Test
//    public void findBySearchTerm_TitleOfFirstTodoEntryContainsGivenSearchTerm_ShouldReturnFirstTodoEntry() {
//        List<Todo> searchResults = repository.findBySearchTerm("iTl");
// 
//        Todo found = searchResults.get(0);
//        assertThat(found.getId()).isEqualTo(1L);
//    }  

}