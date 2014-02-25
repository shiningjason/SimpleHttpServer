package org.shining.net.http.handler;

import org.shining.net.http.Status;

public class ForbiddenHandler extends BaseNotOkHandler {

    @Override
    public Status getNotOkStatus() {
        return Status.FORBIDDEN;
    }

    @Override
    public String getNotOkReason() {
        return "Don't be a bad guy！";
    }
}
