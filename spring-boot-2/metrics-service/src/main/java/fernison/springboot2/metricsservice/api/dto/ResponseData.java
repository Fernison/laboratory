package fernison.springboot2.metricsservice.api.dto;

public class ResponseData {

	public final static int OK=1;
	public final static int KO=99;
	
	private String resultMsg;
	private int codeMsg;

	public ResponseData(String resultMsg, int codeMsg) {
		super();
		this.resultMsg = resultMsg;
		this.codeMsg = codeMsg;
	}
	public ResponseData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public int getCodeMsg() {
		return codeMsg;
	}
	public void setCodeMsg(int codeMsg) {
		this.codeMsg = codeMsg;
	}

	@Override
	public String toString() {
		return "RequestData [resultMsg=" + resultMsg + ", codeMsg=" + codeMsg + "]";
	}
	
}
