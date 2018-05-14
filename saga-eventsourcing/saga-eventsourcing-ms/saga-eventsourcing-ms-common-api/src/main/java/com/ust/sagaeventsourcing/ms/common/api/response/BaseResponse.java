package com.ust.sagaeventsourcing.ms.common.api.response;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    transient private static final long serialVersionUID = -3014332092614464203L;

    public static final Integer CODE_OK = 0;
    public static final String DESC_OK = "Operation successfully";

    public static final Integer CODE_NOK = -1;
    public static final String DESC_NOK = "Operation failed";

    private ResponseInfo responseInfo = new ResponseInfo();

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[RESPONSE_INFO]:").append(responseInfo);
        return sb.toString();
    }

}
