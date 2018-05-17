package tcp.microservices.common.api.request;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class RequestInfo implements Serializable {

    transient private static final long serialVersionUID = -7432446767231955073L;

    private String transactionId = UUID.randomUUID().toString();
    private String channel;
    private String user;
    private Date timestamp;
    private String application;
    private String operationId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
    
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("RequestInfo{").
		append(super.toString()).
		append("[channel]:").append(this.channel).
		append("[user]:").append(this.user).
		append("[timestamp]:").append(this.timestamp).
		append("[application]:").append(this.application).
		append("[operationId]:").append(this.operationId).
		append("}");
		return sb.toString();
	}
}
