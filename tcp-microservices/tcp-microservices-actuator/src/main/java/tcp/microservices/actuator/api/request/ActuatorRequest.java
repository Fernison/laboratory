package tcp.microservices.actuator.api.request;

import tcp.microservices.actuator.api.dto.ActuatorDto;
import tcp.microservices.common.api.request.BaseRequest;

public class ActuatorRequest extends BaseRequest {

	transient private static final long serialVersionUID = 4773358733927312308L;

	private ActuatorDto actuator;
		
	public ActuatorDto getActuator() {
		return actuator;
	}

	public void setActuator(ActuatorDto actuator) {
		this.actuator = actuator;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("ActuatorRequest{");
		sb.append(super.toString());
		sb.append(this.actuator);
		sb.append("}");
		return sb.toString();
	}
}
