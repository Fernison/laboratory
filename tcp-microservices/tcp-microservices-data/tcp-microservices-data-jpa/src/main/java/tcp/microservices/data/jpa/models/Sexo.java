package tcp.microservices.data.jpa.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sexo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String descripcion;
	
	@OneToMany(mappedBy = "sexo") // Una persona tiene One sexo y un sexo pertenece a Many personas
	private Collection<Persona> personas;
	 
	public Sexo(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public Sexo() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Collection<Persona> personas) {
		this.personas = personas;
	}

	@Override
	public String toString() {
		return "Sexo [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
}

