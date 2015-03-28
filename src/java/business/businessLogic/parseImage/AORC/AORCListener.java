/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.parseImage.AORC;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author david
 */
public class AORCListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      //do nothing!
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       if(ParseImageAORC.isInitialized()){
           ParseImageAORC.getInstance().cleanUp();
       }
           
    }
}
