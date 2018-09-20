package fernison.springboot2.metricsservice.api.dto;

public class RequestData {

	private String value1;
	private int value2;
	private int value3;

	public RequestData(String value1, int value2, int value3) {
		super();
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}
	
	public RequestData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public int getValue2() {
		return value2;
	}
	public void setValue2(int value2) {
		this.value2 = value2;
	}
	public int getValue3() {
		return value3;
	}
	public void setValue3(int value3) {
		this.value3 = value3;
	}

	@Override
	public String toString() {
		return "RequestData [value1=" + value1 + ", value2=" + value2 + ", value3=" + value3 + "]";
	}
		
}
