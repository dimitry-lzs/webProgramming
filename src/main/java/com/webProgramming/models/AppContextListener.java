package com.webProgramming.models;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Util.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (Util.getSessionFactory() != null) {
            Util.getSessionFactory().close();
        }
    }
}
