package org.shining.net.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.shining.net.http.MimeType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.shining.net.http.Status.OK;

public class OkHandler implements HttpHandler {

    private static final String CONTENT_TYPE = "Content-Type";
    private File serveFile;

    public OkHandler(File serveFile) {
        this.serveFile = serveFile;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().set(CONTENT_TYPE, MimeType.getContentType(serveFile.getPath()));
        httpExchange.sendResponseHeaders(OK.getCode(), 0);

        OutputStream os = httpExchange.getResponseBody();
        writeFile(serveFile, os);
        os.close();
    }

    private void writeFile(File file, OutputStream os) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        int count;
        byte[] buffer = new byte[0x10000];

        while ((count = fis.read(buffer)) >= 0) os.write(buffer, 0, count);
        fis.close();
    }
}
