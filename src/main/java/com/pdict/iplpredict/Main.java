package com.pdict.iplpredict;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        String host = args[0];
        String port = args[1];
        String baseURI = "http://"+host+":"+port+"/iplpredict";

        final ResourceConfig rc = new PackagesResourceConfig("com.pdict.iplpredict");
        HttpServer httpServer = HttpServerFactory.create(baseURI,rc);
        httpServer.start();
        System.out.println("HTTP SERVER STARTED ON "+baseURI);
    }
}