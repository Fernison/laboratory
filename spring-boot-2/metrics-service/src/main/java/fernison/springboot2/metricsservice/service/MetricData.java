package fernison.springboot2.metricsservice.service;

import java.util.HashMap;

public class MetricData {
	
	private String measurement;
	private String tag;
	private HashMap<String,Integer> fields=new HashMap<String,Integer>();
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public HashMap<String, Integer> getFields() {
		return fields;
	}
	public void setFields(HashMap<String, Integer> fields) {
		this.fields = fields;
	}
	@Override
	public String toString() {
		return "MetricData [measurement=" + measurement + ", tag=" + tag + ", fields=" + fields + "]";
	} 
	
}
