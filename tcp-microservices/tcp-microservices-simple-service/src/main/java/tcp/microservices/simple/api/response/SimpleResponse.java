package tcp.microservices.simple.api.response;

import tcp.microservices.common.api.response.BaseResponse;

public class SimpleResponse extends BaseResponse {

	transient private static final long serialVersionUID = 8121972701361228864L;

	private String data;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("SimpleResponse{");
		sb.append(super.toString());
		sb.append(this.data);
		sb.append("}");
		return sb.toString();

	}
}
