package tcp.microservices.common.api.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseInfo implements Serializable {

    transient private static final long serialVersionUID = 4785939559420938536L;

    private Integer code;
    private String description;
    private Map<String, String> additionalInfo = new HashMap<>();

    public ResponseInfo() {
        super();
    }

    public ResponseInfo(Integer code, String description) {
        this();
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, String> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[CODE]:").append(code);
        sb.append("|[DESC]:").append(description);
        return sb.toString();
    }

}
