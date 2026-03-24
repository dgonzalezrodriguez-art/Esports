package esports.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // La ruta exacta a tu base de datos
    private static final String URL = "jdbc:mariadb://localhost:3306/esports";
    // El usuario por defecto de XAMPP
    private static final String USUARIO = "root";
    // Contraseña vacía por defecto en XAMPP
    private static final String CONTRASENA = "1234";

    public static Connection conectar() throws SQLException {
        // Aquí le obligamos a usar el usuario "root" siempre
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}