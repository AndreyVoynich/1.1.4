package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static SessionFactory factory;

    private static Connection connection;

    private Util() {
    }

    private static final String URL = "jdbc:mysql://localhost:3306/sqlbase";
    private static final String PASSWORD = "root";
    private static final String USER = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("connect error");
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            try {
                Properties properties = new Properties();

                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USER);
                properties.put(Environment.PASS, PASSWORD);
                properties.put(Environment.DRIVER, DRIVER);
                properties.put(Environment.DIALECT, DIALECT);

                factory = new Configuration()
                        .addProperties(properties)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();

                Session session = factory.getCurrentSession();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return factory;
    }
}













                /*registryBuilder.applySettings(settings);

                registry = registryBuilder.build();

                MetadataSources sources = new MetadataSources(registry);

                sources.addAnnotatedClass(User.class);
                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void close() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}*/
