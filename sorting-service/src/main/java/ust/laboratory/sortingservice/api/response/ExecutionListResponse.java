package ust.laboratory.sortingservice.api.response;

import java.io.Serializable;
import java.util.List;

import ust.laboratory.sortingservice.api.dto.Execution;

public class ExecutionListResponse implements Serializable {

	transient private static final long serialVersionUID = 1134554639503238072L;

	private List<Execution> executions;

	public List<Execution> getExecutions() {
		return executions;
	}

	public void setExecutions(List<Execution> executions) {
		this.executions = executions;
	}

	@Override
	public String toString() {
		return "ExecutionListResponse [executions=" + executions + "]";
	}
		
}
