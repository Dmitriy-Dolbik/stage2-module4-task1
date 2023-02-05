package com.mjc.stage2.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mjc.stage2.ConnectionFactory;

public class H2ConnectionFactory implements ConnectionFactory
{
    @Override
    public Connection createConnection()
    {
        Properties properties = new Properties();
        try (InputStream inputStream = Connection.class.getClassLoader()
                .getResourceAsStream("h2database.properties")){
            properties.load(inputStream);
            String driver = properties.getProperty("jdbc_driver");
            String url = properties.getProperty("db_url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            Class.forName(driver);

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | IOException | ClassNotFoundException e){
            e.printStackTrace();;
            return null;
        }
    }
}

