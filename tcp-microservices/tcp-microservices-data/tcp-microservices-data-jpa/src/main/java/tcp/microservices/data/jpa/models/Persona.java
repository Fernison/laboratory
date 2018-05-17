package tcp.microservices.data.jpa.models;
 
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

 
@Entity
@Table(name="Persona", uniqueConstraints={@UniqueConstraint(columnNames={"dni"})})
@NamedQuery(name = "Persona.findCustomized", query = "select p from Persona p where p.dni = :dni")
public class Persona {
          
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String dni;     
	private String nombre;
	private String apellidos;
	private Date fecha_alta;
	
	@ManyToOne // Un sexo pertenece a Many personas y una persona tiene One sexo
	// De la linea de abajo. Hay que decirle que "insertable" es true. Si no, no lo inserta y
	// dice que ID_SEXO no puede tener valor NULL
	@JoinColumn(name = "ID_SEXO", nullable = false ,insertable = true,updatable = true)
	private Sexo sexo;
		
	@OneToMany(mappedBy = "persona") // Una persona tiene MANY hobbies y un hobbie es de ONE persona
	private Collection<Hobbie> hobbies;
	
	public Persona() {	}
	
	public Persona(int id, String dni, String nombre, String apellidos, Date fecha_alta) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_alta = fecha_alta;
	}

	public Persona(String dni, String nombre, String apellidos, Date fecha_alta) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_alta = fecha_alta;
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Collection<Hobbie> getHobbies() {
		return hobbies;
	}
	public void setHobbies(Collection<Hobbie> hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", fecha_alta=" + fecha_alta + ", sexo=" + sexo + ", hobbies=" + hobbies + "]";
	}

}
