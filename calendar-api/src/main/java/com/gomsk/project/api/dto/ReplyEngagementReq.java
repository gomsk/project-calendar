package com.gomsk.project.api.dto;

import com.gomsk.project.core.domain.RequestReplyType;

public class ReplyEngagementReq {
    private RequestReplyType type;  //REJECT, ACCEPT

    public ReplyEngagementReq(){

    }

    public ReplyEngagementReq(RequestReplyType type) {
        this.type = type;
    }

    public RequestReplyType getType() {
        return type;
    }
}
