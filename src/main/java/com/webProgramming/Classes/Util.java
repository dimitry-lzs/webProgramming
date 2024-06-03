package com.webProgramming.Classes;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.JAKARTA_JDBC_DRIVER, "org.sqlite.JDBC");
                settings.put(Environment.JAKARTA_JDBC_URL, "jdbc:sqlite:vietnam.db");
                settings.put(Environment.JAKARTA_JDBC_USER, "");
                settings.put(Environment.JAKARTA_JDBC_PASSWORD, "");
                settings.put(Environment.DIALECT, "org.hibernate.community.dialect.SQLiteDialect");

                System.out.println("Hibernate Java Config serviceRegistry created");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Admin.class);
                configuration.addAnnotatedClass(Program.class);
                // configuration.addAnnotatedClass(Bill.class);
                // configuration.addAnnotatedClass(Call.class);
                configuration.addAnnotatedClass(Seller.class);
                // configuration.addAnnotatedClass(Client.class);


                configuration.setProperties(settings);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                System.out.println("Problem creating session factory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}