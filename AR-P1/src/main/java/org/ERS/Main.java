package org.ERS;

import jakarta.servlet.*;
import org.ERS.Auth.AuthService;
import org.ERS.Auth.AuthServlet;
import org.ERS.User.UserDAO;
import org.ERS.User.UserService;
import org.ERS.User.UserServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws LifecycleException {
        String docBase = System.getProperty("java.io.tmpdir");
        Tomcat webServer = new Tomcat();




        // Web server base configurations
        webServer.setBaseDir(docBase);
        webServer.setPort(5000); // defaults to 8080, but we can set it to whatever port we want (as long as its open)
        webServer.getConnector(); // formality, required in order for the server to receive requests

        // App component instantiation
        UserDAO userDAO = new UserDAO();
        AuthService authService = new AuthService(userDAO);
        UserService userService = new UserService(userDAO);
        UserServlet userServlet = new UserServlet(userService);
        AuthServlet authServlet = new AuthServlet(authService);

        // Web server context and servlet configurations
        final String rootContext = "/taskmaster";
        webServer.addContext(rootContext, docBase);
        webServer.addServlet(rootContext, "UserServlet", (Servlet) userServlet).addMapping("/users");
        webServer.addServlet(rootContext, "AuthServlet", (Servlet) authServlet).addMapping("/auth");

        // Starting and awaiting web requests
        webServer.start();
        webServer.getServer().await();

    }
}