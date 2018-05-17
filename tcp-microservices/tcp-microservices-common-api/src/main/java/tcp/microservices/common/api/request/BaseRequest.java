package tcp.microservices.common.api.request;

import java.io.Serializable;

public class BaseRequest implements Serializable {

    transient private static final long serialVersionUID = 7123260470197328094L;

    private RequestInfo requestInfo = new RequestInfo();

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[REQUEST_INFO]:").append(requestInfo);
        return sb.toString();
    }


}
