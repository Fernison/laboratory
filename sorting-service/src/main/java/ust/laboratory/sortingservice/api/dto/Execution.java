package ust.laboratory.sortingservice.api.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_DEFAULT)
public class Execution implements Serializable {

	transient private static final long serialVersionUID = 1134554639503238072L;

	private UUID id;
	private Date timestamp;
	private long duration;
	private EnumStatus status;
	private int[] input;
	private int[] output;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public int[] getInput() {
		return input;
	}

	public void setInput(int[] input) {
		this.input = input;
	}

	public int[] getOutput() {
		return output;
	}

	public void setOutput(int[] output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "Executions [id=" + id + ", timestamp=" + timestamp + ", duration=" + duration + ", status=" + status
				+ ", input=" + Arrays.toString(input) + ", output=" + Arrays.toString(output) + "]";
	}
	
}
