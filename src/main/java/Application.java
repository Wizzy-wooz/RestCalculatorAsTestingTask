package main.java;

import com.sun.net.httpserver.HttpServer;
import main.java.handler.RestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

/**
 * Created by elena on 1/12/17.
 */
public class Application {

    private static final int PORT_NUMBER = 8081;
    private static final int NUMBER_OF_THREADS = 10;

    private static Logger logger = Logger.getLogger(Application.class.getName());

    public static final void main(String[] args) {
        HttpServer server;

        try {
            server = HttpServer.create(new InetSocketAddress(PORT_NUMBER), 0);
        } catch (IOException e) {
            logger.warning(e.getMessage());
            return;
        }

        server.createContext(RestHandler.URL_SEPARATOR, new RestHandler());
        server.setExecutor(java.util.concurrent.Executors.newFixedThreadPool(NUMBER_OF_THREADS));
        logger.info("Started listening on port: " + PORT_NUMBER);
        server.start();
    }
}
