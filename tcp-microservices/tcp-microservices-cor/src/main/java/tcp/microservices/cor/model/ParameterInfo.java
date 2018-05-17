package tcp.microservices.cor.model;

import java.io.Serializable;

public class ParameterInfo implements Serializable {


	transient private static final long serialVersionUID = -1841019418955953607L;

	private String name;		
	private String type;
	private String jsonValue;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getJsonValue() {
		return jsonValue;
	}
	
	public void setJsonValue(String jsonValue) {
		this.jsonValue = jsonValue;
	}
	
	@Override
	public String toString() {
		return "ParameterInfo = {name=" + name + ", type=" + type + ", jsonValue=" + jsonValue + "}";
	}
}
