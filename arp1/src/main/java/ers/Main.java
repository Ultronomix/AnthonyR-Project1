package ers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ers.auth.AuthService;
import ers.auth.AuthServlet;
import ers.rembursement.ReimbDAO;
import ers.rembursement.ReimbService;
import ers.rembursement.ReimbServlet;
import ers.users.UserDAO;
import ers.users.UserService;
import ers.users.UserServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws LifecycleException {

        logger.info("Initializing ERS...");

        String docBase = System.getProperty("java.io.tmpdir");
        Tomcat webServer = new Tomcat();


        webServer.setBaseDir(docBase);
        webServer.setPort(5000);
        webServer.getConnector();

        UserDAO userDAO = new UserDAO();
        ReimbDAO reimbDAO = new ReimbDAO();
        AuthService verifyService = new AuthService(userDAO);
        UserService userService = new UserService(userDAO);
        ReimbService reimbService = new ReimbService(reimbDAO);
        ObjectMapper jsonMapper = new ObjectMapper();
        UserServlet userServlet = new UserServlet(userService, jsonMapper);
        AuthServlet verifyServlet = new AuthServlet(verifyService, jsonMapper);
        ReimbServlet reimbServlet = new ReimbServlet(reimbService, jsonMapper);

        final String rootContext = "/arp1";
        webServer.addContext(rootContext, docBase);
        webServer.addServlet(rootContext, "UserServlet", userServlet).addMapping("/users");
        webServer.addServlet(rootContext, "AuthServlet", verifyServlet).addMapping("/auth");
        webServer.addServlet(rootContext, "ReimbServlet", reimbServlet).addMapping("/reimburse");
        webServer.start();
        logger.info("ERS Initialized!");
        webServer.getServer().await();

    }


}


