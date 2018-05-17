package tcp.microservices.cor.model;

import java.io.Serializable;
import java.util.List;

public class LogInfoData implements Serializable {
	
	transient private static final long serialVersionUID = 5454580557576479632L;
	
	private LogInfoType type;
	private String interceptor;
	private String targetClass;
	private String method;
	private String binding;
	private List<ParameterInfo> input;		
	private ParameterInfo output;
	
	public LogInfoData(final LogInfoType type, final String interceptor, final String targetClass, final String method) {
		super();
		this.type = type;
		this.interceptor = interceptor;
		this.targetClass = targetClass;
		this.method = method;
	}
	
	public LogInfoType getType() {
		return type;
	}

	public void setType(LogInfoType type) {
		this.type = type;
	}

	public String getInterceptor() {
		return interceptor;
	}

	public void setInterceptor(String interceptor) {
		this.interceptor = interceptor;
	}

	public String getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public List<ParameterInfo> getInput() {
		return input;
	}

	public void setInput(List<ParameterInfo> input) {
		this.input = input;
	}

	public ParameterInfo getOutput() {
		return output;
	}

	public void setOutput(ParameterInfo output) {
		this.output = output;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("LogInfoData = {");
		sb.append("type=" + type + ", interceptor=" + interceptor + ", targetClass=" + targetClass
				+ ", method=" + method);
		sb.append(this.type == LogInfoType.EVENT? ", binding=" + binding : "");
		sb.append(", input=" + input + ", output=" + output + "]");
		return sb.toString();
	}

}
