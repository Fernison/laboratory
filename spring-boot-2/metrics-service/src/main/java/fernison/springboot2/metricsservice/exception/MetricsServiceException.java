package fernison.springboot2.metricsservice.exception;

public class MetricsServiceException extends Exception {
	
	transient private static final long serialVersionUID = 1134554639503238072L;

	private String errMsg;
	private int code;
		
	public static final int INTERNAL_ERROR=99;
	
	public MetricsServiceException(String errMsg, int code) {
		this.errMsg=errMsg;
		this.code=code;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MetricsServiceException [errMsg=" + errMsg + ", code=" + code + "]";
	}
		
}
