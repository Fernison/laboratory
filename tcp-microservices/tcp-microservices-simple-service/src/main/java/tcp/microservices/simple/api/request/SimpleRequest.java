package tcp.microservices.simple.api.request;

import tcp.microservices.common.api.request.BaseRequest;
import tcp.microservices.simple.api.dto.SimpleDto;

public class SimpleRequest extends BaseRequest {

	transient private static final long serialVersionUID = 4773358733927312308L;

	private SimpleDto data;
		
	public SimpleDto getData() {
		return data;
	}

	public void setData(SimpleDto data) {
		this.data = data;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("SimpleRequest{");
		sb.append(super.toString());
		sb.append(this.data);
		sb.append("}");
		return sb.toString();
	}
}
