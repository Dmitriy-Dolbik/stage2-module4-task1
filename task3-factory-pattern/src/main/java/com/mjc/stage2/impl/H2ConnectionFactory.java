package com.mjc.stage2.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mjc.stage2.ConnectionFactory;

public class H2ConnectionFactory implements ConnectionFactory
{
    private static Properties properties;
    static
    {
        properties = new Properties();
        try (InputStream input = H2ConnectionFactory.class.getClassLoader()
                .getResourceAsStream("h2database.properties"))
        {
            properties.load(input);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
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
            try
            {
                Class.forName(properties.getProperty("jdbc_driver"));
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }

            return DriverManager.getConnection(
                    properties.getProperty("db_url"),
                    properties.getProperty("user"),
                    properties.getProperty("password")
            );
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

