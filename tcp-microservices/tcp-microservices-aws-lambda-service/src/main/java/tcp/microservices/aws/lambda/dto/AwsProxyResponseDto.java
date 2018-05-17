package tcp.microservices.aws.lambda.dto;

import java.util.Map;

public class AwsProxyResponseDto extends AwsProxyDto {

	private int statusCode; 

	public AwsProxyResponseDto(boolean isBase64Encoded, int statusCode, Map<String, String> headers, String body) {
		super(isBase64Encoded, headers, body);
		this.statusCode = statusCode;
	}

	public AwsProxyResponseDto() {
		super();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
