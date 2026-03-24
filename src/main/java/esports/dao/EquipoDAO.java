package esports.dao;

import esports.excepciones.ExcepcionDeCarga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> obtenerEquiposPorJuego(int idJuego) throws ExcepcionDeCarga {
        List<String> equipos = new ArrayList<>();
        String sql = "SELECT id_equipo, nombre FROM equipo WHERE id_juego = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idJuego);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    equipos.add("ID Equipo: " + rs.getInt("id_equipo") + " - Nombre: " + rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            throw new ExcepcionDeCarga("Error al buscar equipos: " + e.getMessage());
        }
        return equipos;
    }
}