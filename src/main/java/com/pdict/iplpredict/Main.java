package com.pdict.iplpredict;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.ResourceConfig;

public class Main {

    public static final String BASE_URI = "http://localhost:8081/myapp/";

    public static void main(String[] args) throws Exception {

        final ResourceConfig rc = new PackagesResourceConfig("com.pdict.iplpredict");
        HttpServer httpServer = HttpServerFactory.create(BASE_URI,rc);
        httpServer.start();
        System.out.println("enter to stop");
        System.in.read();
        httpServer.stop(0);
    }
}