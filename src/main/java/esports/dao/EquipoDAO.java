package esports.dao;

import esports.excepciones.ExcepcionDeCarga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EquipoDAO {

    public void insertarEquipo(String nombre, int idJuego) throws ExcepcionDeCarga {
        String sql = "INSERT INTO equipo (nombre, id_juego) VALUES (?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setInt(2, idJuego);
            pstmt.executeUpdate();
            System.out.println("¡Equipo '" + nombre + "' creado correctamente!");

        } catch (SQLException e) {
            throw new ExcepcionDeCarga("Error al crear el equipo: " + e.getMessage());
        }
    }
}