package ust.laboratory.sortingservice.exception;

public class SortingServiceException extends Exception {
	
	transient private static final long serialVersionUID = 1134554639503238072L;

	private String errMsg;
	private int code;
		
	public static final int ID_NOT_FOUND=1;
	public static final int INVALID_INPUT_DATA=2;
	public static final int INTERNAL_ERROR=99;
	
	public SortingServiceException(String errMsg, int code) {
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
	
}
