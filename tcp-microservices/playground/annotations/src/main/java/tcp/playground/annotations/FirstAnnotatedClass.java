package tcp.playground.annotations;

import org.springframework.stereotype.Component;

@Component
@Findable(name = "Find me")
public class FirstAnnotatedClass {

	@FindableMethod(text="texto", value="valor")
	public String metodo(String arg) {
		return "metodo "+arg;
	}
}