package org.shining.net.http.handler;

import org.shining.net.http.Status;

public class InternalServerErrorHandler extends BaseNotOkHandler {

    @Override
    public Status getNotOkStatus() {
        return Status.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getNotOkReason() {
        return "wow ：-（";
    }
}
