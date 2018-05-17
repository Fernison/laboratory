package tcp.microservices.aws.lambda.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AwsProxyDto {

	private boolean isisBase64Encoded;
	private Map<String, String> headers;
	private String body;
	
	public AwsProxyDto(boolean isBase64Encoded, Map<String, String> headers, String body) {
		this.isisBase64Encoded=isBase64Encoded;
		this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
		this.body = body;
	}

	public AwsProxyDto() {
		super();
	}

	public boolean isIsBase64Encoded() {
		return isisBase64Encoded;
	}

	public void setIsBase64Encoded(boolean isisBase64Encoded) {
		this.isisBase64Encoded = isisBase64Encoded;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
		
}
