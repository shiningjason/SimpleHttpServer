package org.shining.net.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.shining.net.http.Status;

import java.io.IOException;
import java.io.OutputStream;

public abstract class BaseNotOkHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        Status status = getNotOkStatus();
        String response = status.getMessage();
        String reason = getNotOkReason();
        if (reason != null) response += "：" + reason;

        httpExchange.sendResponseHeaders(status.getCode(), response.getBytes().length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public abstract Status getNotOkStatus();

    public abstract String getNotOkReason();
}
