package ust.laboratory.sortingservice.api.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_DEFAULT)
@ApiModel(value = "Execution", description = "A sort execution")
public class Execution implements Serializable {

	transient private static final long serialVersionUID = 1134554639503238072L;

	@ApiModelProperty(value = "The unique identifier of the given execution", readOnly = true, required = true)
	private UUID id;
	private Date timestamp;
	private long duration;
	@ApiModelProperty(value = "The status of the execution execution", required = true)
	private EnumStatus status;
	@ApiModelProperty(value = "The array to sort")
	private int[] input;
	@ApiModelProperty(value = "The sorted array")
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
