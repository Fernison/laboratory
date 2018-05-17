package tcp.microservices.data.jpa.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Hobbie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String descripcion;     
	
	@ManyToOne(fetch = FetchType.LAZY) // Una persona tiene MANY hobbies y un hobbie es de ONE persona
	@JoinColumn(name = "ID_PERSONA", nullable = false ,insertable = true,updatable = true)
	private Persona persona;

	public Hobbie() {
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Hobbie [id=" + id + ", descripcion=" + descripcion + "]";
	}

}
