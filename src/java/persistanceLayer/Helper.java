/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistanceLayer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author david
 */
public abstract class Helper implements ServletContextListener {

    protected static String dbHost;
    protected static String dbUser;
    protected static String dbPassword;

    public static DBHandler getDBHandler() {
        return null;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Do nothing
    }
}
