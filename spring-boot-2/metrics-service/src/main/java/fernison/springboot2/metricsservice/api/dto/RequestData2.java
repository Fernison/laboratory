package fernison.springboot2.metricsservice.api.dto;

public class RequestData2 extends RequestData {

	private MiRequestData2 miRequestData2;
	
	public RequestData2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestData2(MiRequestData2 miRequestData2) {
		super();
		this.miRequestData2 = miRequestData2;
	}

	public MiRequestData2 getMiRequestData2() {
		return miRequestData2;
	}

	public void setMiRequestData2(MiRequestData2 miRequestData2) {
		this.miRequestData2 = miRequestData2;
	}
	
	@Override
	public String toString() {
		return "RequestData2 ["+super.toString()+", miRequestData2=" + miRequestData2 + "]";
	}

	class MiRequestData2 {
		
		private int value1;
		private int value2;
		
		public MiRequestData2(int value1, int value2) {
			super();
			this.value1 = value1;
			this.value2 = value2;
		}
		public MiRequestData2() {
			super();
			// TODO Auto-generated constructor stub
		}
		public int getValue1() {
			return value1;
		}
		public void setValue1(int value1) {
			this.value1 = value1;
		}
		public int getValue2() {
			return value2;
		}
		public void setValue2(int value2) {
			this.value2 = value2;
		}
		@Override
		public String toString() {
			return "MiRequestData2 [value1=" + value1 + ", value2=" + value2 + "]";
		}
		
	}
		
}
