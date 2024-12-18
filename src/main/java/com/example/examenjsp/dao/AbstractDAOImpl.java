package com.example.examenjsp.dao;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;

public class AbstractDAOImpl {
    protected static String driverClassName;
    protected static String dbUrl;
    protected static String schemaUrl;
    protected static String schema;
    protected static String username;
    protected static String password;

    static {
        Properties properties = new Properties();
        try {
            properties.load(AbstractDAOImpl.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driverClassName = properties.getProperty("jdbc.driverClassName");
        dbUrl = properties.getProperty("jdbc.url");
        schema = properties.getProperty("jdbc.schema");
        schemaUrl = dbUrl + schema;
        username = properties.getProperty("jdbc.username");
        password = properties.getProperty("jdbc.password");
    }


    /**
     * Ejecuta un PreparedStatement de tipo insert.
     * @param ps de tipo insert
     * @return devuelve Optional de entero correspondiente al ID generado.
     * @throws SQLException
     */
    protected Optional<Integer> executeInsert(PreparedStatement ps) throws SQLException {

        int rowNum = ps.executeUpdate();
        if (rowNum == 0) {
            System.out.println("Sentencia DML(INSERT, UPDATE o DELETE) cero filas actualizadas.");
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return Optional.of(generatedKeys.getInt(1));
            } else {
                System.out.println("Sentencia no genera ID .");
                return Optional.empty();
            }
        } catch(SQLFeatureNotSupportedException e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }

    protected void executeUpdate(PreparedStatement ps) throws SQLException  {
        int rowNum = ps.executeUpdate();
        if (rowNum == 0) {
            System.out.println("Sentencia DML(INSERT, UPDATE o DELETE) con cero filas actualizadas.");
        }
    }

    protected PreparedStatement prepareStmtGeneratedKeys(Connection conn, String query) throws SQLException {
        return conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }

    protected PreparedStatement prepareStmtGeneratedKeys(Connection conn, String query,String[] indexNames) throws SQLException {
        return conn.prepareStatement(query, indexNames);
    }

    protected static Connection connectDB() throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        Connection conn = DriverManager.getConnection(schemaUrl, username, password);
        return conn;

    }

    protected static void closeDb(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
