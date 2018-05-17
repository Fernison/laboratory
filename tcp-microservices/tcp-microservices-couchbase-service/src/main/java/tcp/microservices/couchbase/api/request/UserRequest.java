package tcp.microservices.couchbase.api.request;

import tcp.microservices.common.api.request.BaseRequest;
import tcp.microservices.couchbase.api.dto.UserDto;

public class UserRequest extends BaseRequest {

	transient private static final long serialVersionUID = 4773358733927312308L;

	private UserDto user;
		
	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("UserRequest{");
		sb.append(super.toString());
		sb.append(this.user);
		sb.append("}");
		return sb.toString();
	}
}
