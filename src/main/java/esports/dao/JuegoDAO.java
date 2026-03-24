package esports.dao;

import esports.excepciones.ExcepcionDeCarga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JuegoDAO {

    public String obtenerNombreJuegoPorId(int idJuego) throws ExcepcionDeCarga {
        String nombre = "Desconocido";
        String sql = "SELECT nombre FROM juego WHERE id_juego = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJuego);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }
        } catch (Exception e) {
            throw new ExcepcionDeCarga("No se pudo obtener el nombre del juego: " + e.getMessage());
        }
        return nombre;
    }

    public String obtenerNombreJuegoPorJugador(int idJugador) throws ExcepcionDeCarga {
        String nombre = "Desconocido";
        String sql = "SELECT j.nombre AS nombre_juego " +
                "FROM jugador ju " +
                "JOIN equipo e ON ju.id_equipo = e.id_equipo " +
                "JOIN juego j ON e.id_juego = j.id_juego " +
                "WHERE ju.id_jugador = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJugador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre_juego");
            }
        } catch (Exception e) {
            throw new ExcepcionDeCarga("No se pudo obtener el nombre del juego: " + e.getMessage());
        }
        return nombre;
    }



}