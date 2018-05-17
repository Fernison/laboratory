package tcp.microservices.aop.api.request;

import tcp.microservices.common.api.request.BaseRequest;
import tcp.microservices.aop.api.dto.AopDto;

public class AopRequest extends BaseRequest {

	transient private static final long serialVersionUID = 4773358733927312308L;

	private AopDto aop;
		
	public AopDto getAop() {
		return aop;
	}

	public void setAop(AopDto aop) {
		this.aop = aop;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("AopRequest{");
		sb.append(super.toString());
		sb.append(this.aop);
		sb.append("}");
		return sb.toString();
	}
}
