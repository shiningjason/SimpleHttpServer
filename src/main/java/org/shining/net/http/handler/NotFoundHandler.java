package org.shining.net.http.handler;

import org.shining.net.http.Status;

public class NotFoundHandler extends BaseNotOkHandler {

    @Override
    public Status getNotOkStatus() {
        return Status.NOT_FOUND;
    }

    @Override
    public String getNotOkReason() {
        return "Oops！";
    }
}
