package tcp.microservices.aws.lambda.dto;

import java.util.Map;

public class AwsProxyRequestDto extends AwsProxyDto {

	public AwsProxyRequestDto(boolean isBase64Encoded,Map<String, String> headers, String body) {
		super(isBase64Encoded, headers, body);
	}

	public AwsProxyRequestDto() {
		super();
	}
		
}
