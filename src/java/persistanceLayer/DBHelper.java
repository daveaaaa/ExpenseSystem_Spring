package persistanceLayer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author david
 */
public abstract class DBHelper implements ServletContextListener {

    protected static String dbHost;
    protected static String dbUsername;
    protected static String dbPassword;
    protected static String dbName;

    public static DBHandler getDBHandler() throws Exception {
        return new NullDBHandler();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dbHost = sce.getServletContext().getInitParameter("dbHost");
        dbName = sce.getServletContext().getInitParameter("dbName");
        dbUsername = sce.getServletContext().getInitParameter("dbUser");
        dbPassword = sce.getServletContext().getInitParameter("dbPassword");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Do nothing
    }
}
