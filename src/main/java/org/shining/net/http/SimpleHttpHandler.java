package org.shining.net.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.shining.net.http.handler.ForbiddenHandler;
import org.shining.net.http.handler.InternalServerErrorHandler;
import org.shining.net.http.handler.NotFoundHandler;
import org.shining.net.http.handler.OkHandler;

import java.io.File;
import java.io.IOException;

public class SimpleHttpHandler implements HttpHandler {

    private static final String INDEX_FILE = "index.html";

    private String contentDir;

    public SimpleHttpHandler(String contentDir) {
        this.contentDir = contentDir;
    }

    public void handle(HttpExchange httpExchange) throws IOException {

        String path = httpExchange.getRequestURI().getPath();
        if (path.equals("/")) path += INDEX_FILE;

        File file = new File(contentDir + path).getCanonicalFile();
        HttpHandler handler;

        if (path.contains(".."))
            handler = getStatusHandler(Status.FORBIDDEN);
        else if (!file.isFile())
            handler = getStatusHandler(Status.NOT_FOUND);
        else
            handler = getStatusHandler(Status.OK, file);

        try {
            handler.handle(httpExchange);
        } catch (IOException e) {
            getStatusHandler(Status.INTERNAL_SERVER_ERROR).handle(httpExchange);
        }
    }

    private HttpHandler getStatusHandler(Status status, Object... params) {
        switch (status) {
            case OK:
                if (params == null || params.length == 0 || !(params[0] instanceof File))
                    throw new IllegalArgumentException("Second parameter must be a File object");

                return new OkHandler((File) params[0]);

            case FORBIDDEN:
                return new ForbiddenHandler();

            case NOT_FOUND:
                return new NotFoundHandler();

            case INTERNAL_SERVER_ERROR:
                return new InternalServerErrorHandler();
        }
        throw new IllegalArgumentException("First parameter - status is wrong");
    }
}
