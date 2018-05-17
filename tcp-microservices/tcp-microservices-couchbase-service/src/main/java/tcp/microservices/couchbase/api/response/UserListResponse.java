package tcp.microservices.couchbase.api.response;

import java.util.ArrayList;
import java.util.List;

import tcp.microservices.common.api.response.BaseResponse;
import tcp.microservices.couchbase.api.dto.UserDto;

public class UserListResponse extends BaseResponse {

	transient private static final long serialVersionUID = 8121972701361228864L;

	private List<UserDto> users = new ArrayList<>(0);

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("UserListResponse{");
		sb.append(super.toString());
		sb.append("|");
		sb.append(users);
		sb.append("}");
		return sb.toString();

	}
}	
