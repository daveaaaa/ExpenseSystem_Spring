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
        return new NullDBHandler();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dbHost = sce.getServletContext().getInitParameter("db");
        dbUser = sce.getServletContext().getInitParameter("dbUser");
        dbPassword = sce.getServletContext().getInitParameter("dbPassword");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Do nothing
    }
}
