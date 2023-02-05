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
    private String driver;
    private String url;
    private String user;
    private String password;

    {
        Properties properties = new Properties();
        try (InputStream input = H2ConnectionFactory.class.getClassLoader()
                .getResourceAsStream("app.properties"))
        {
            properties.load(input);
            driver = properties.getProperty("postgres.driver");
            url = properties.getProperty("postgres.url");
            password = properties.getProperty("postgres.password");
            user = properties.getProperty("postgres.name");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Connection createConnection()
    {
        try
        {
            Class.forName(driver);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            return DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }
}

