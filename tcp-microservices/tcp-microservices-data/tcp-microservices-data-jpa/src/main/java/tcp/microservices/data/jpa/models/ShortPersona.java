package tcp.microservices.data.jpa.models;
 
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShortPersona {
          
	@Id
	private int id;
	private String dni;     
	private String nombre;
	
	public ShortPersona() {	}
	
	public ShortPersona(int id, String dni, String nombre) {
		super();
		this.id=id;
		this.dni = dni;
		this.nombre = nombre;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", dni=" + dni + ", nombre=" + nombre + "]";
	}

}
