package esports.dao;

import esports.excepciones.ExcepcionDeCarga;
import esports.modelo.Jugador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {


    public void insertarJugador(Jugador j) throws ExcepcionDeCarga {
        String sql = "INSERT INTO jugador(nombre, rol, id_equipo) VALUES(?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, j.getNombre());
            stmt.setString(2, j.getRol());
            stmt.setInt(3, j.getIdEquipo());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new ExcepcionDeCarga("No se pudo insertar el jugador: " + e.getMessage());
        }
    }


    public List<Jugador> obtenerTodosLosJugadoresConJuego() throws ExcepcionDeCarga {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT ju.id_jugador, ju.nombre, ju.rol, ju.id_equipo " +
                "FROM jugador ju " +
                "JOIN equipo e ON ju.id_equipo = e.id_equipo " +
                "JOIN juego j ON e.id_juego = j.id_juego";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Jugador j = new Jugador(
                        rs.getInt("id_jugador"),
                        rs.getString("nombre"),
                        rs.getString("rol"),
                        rs.getInt("id_equipo")
                );
                jugadores.add(j);
            }
        } catch (Exception e) {
            throw new ExcepcionDeCarga("Error al obtener los jugadores: " + e.getMessage());
        }
        return jugadores;
    }


    public void modificarJugador(int idJugador, String nombre, String rol, int idEquipo) throws ExcepcionDeCarga {
        String sql = "UPDATE jugador SET nombre = ?, rol = ?, id_equipo = ? WHERE id_jugador = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, rol);
            stmt.setInt(3, idEquipo);
            stmt.setInt(4, idJugador);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new ExcepcionDeCarga("No se pudo modificar el jugador: " + e.getMessage());
        }
    }


    public void borrarJugador(int idJugador) throws ExcepcionDeCarga {
        String sql = "DELETE FROM jugador WHERE id_jugador = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJugador);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new ExcepcionDeCarga("No se pudo borrar el jugador: " + e.getMessage());
        }
    }

    public List<String> obtenerJugadoresConEquipo() throws ExcepcionDeCarga {
        List<String> resultados = new ArrayList<>();

        String sql = "SELECT j.id_jugador, j.nombre, j.rol, e.nombre AS nombre_equipo " +
                "FROM jugador j JOIN equipo e ON j.id_equipo = e.id_equipo";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                String info = "ID: " + rs.getInt("id_jugador") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | Rol: " + rs.getString("rol") +
                        " | Equipo: " + rs.getString("nombre_equipo");
                resultados.add(info);
            }
        } catch (SQLException e) {
            throw new ExcepcionDeCarga("Error al hacer el JOIN: " + e.getMessage());
        }

        return resultados;
    }
    public List<String> obtenerJugadoresPorEquipo(int idEquipo) throws ExcepcionDeCarga {
        List<String> jugadores = new ArrayList<>();
        String sql = "SELECT id_jugador, nombre, rol FROM jugador WHERE id_equipo = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEquipo);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    jugadores.add("ID: " + rs.getInt("id_jugador") + " | Nombre: " + rs.getString("nombre") + " | Rol: " + rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            throw new ExcepcionDeCarga("Error al buscar jugadores: " + e.getMessage());
        }
        return jugadores;
    }
}