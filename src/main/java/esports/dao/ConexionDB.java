package esports.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mariadb://localhost:3306/esports";

    private static final String USUARIO = "root";

    private static final String CONTRASENA = "1234";

    public static Connection conectar() throws SQLException {

        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}