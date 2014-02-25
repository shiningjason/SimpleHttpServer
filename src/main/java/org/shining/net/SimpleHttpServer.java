package org.shining.net;

import com.sun.net.httpserver.HttpServer;
import org.shining.net.http.SimpleHttpHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    private static final int DEFAULT_PORT = 8080;
    private static final String DEFAULT_CONTENT_DIR = "static";

    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;
        String contentDir = null;

        if (args != null && args.length > 0) {
            if (args[0].matches("\\d+"))
                port = Integer.parseInt(args[0]);
            else
                contentDir = args[0];

            if (args.length > 1 && contentDir == null)
                contentDir = args[1];
        }
        if (contentDir == null) contentDir = DEFAULT_CONTENT_DIR;

        System.out.println(
                "Serving HTTP on http://localhost:" + port + ", and content directory is " + contentDir + "!\n" +
                "PS. You can press Ctrl + C to stop me! ：）");

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new SimpleHttpHandler(contentDir));
        server.start();
    }
}
