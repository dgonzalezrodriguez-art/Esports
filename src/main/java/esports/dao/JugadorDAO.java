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


    public void insertarJugador(Jugador jugador) throws ExcepcionDeCarga {
        String sql = "INSERT INTO jugador (nombre, rol, id_equipo) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getRol());
            pstmt.setInt(3, jugador.getIdEquipo());
            pstmt.executeUpdate();
            System.out.println("Jugador insertado correctamente");
        } catch (SQLException e) {
            throw new ExcepcionDeCarga("Error al insertar el jugador: " + e.getMessage());
        }
    }


    public List<Jugador> obtenerTodosLosJugadores() throws ExcepcionDeCarga {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugador";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                jugadores.add(new Jugador(
                        rs.getInt("id_jugador"),
                        rs.getString("Nombre"),
                        rs.getString("rol"),
                        rs.getInt("id_equipo")
                ));
            }
        } catch (SQLException e) {
            throw new ExcepcionDeCarga("Error al obtener los jugadores: " + e.getMessage());
        }
        return jugadores;
    }


    public void modificarJugador(int idJugador, String nuevoNombre, String nuevoRol, int nuevoIdEquipo) throws ExcepcionDeCarga {
        String sql = "UPDATE jugador SET nombre = ?, rol = ?, id_equipo = ? WHERE id_jugador = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, nuevoRol);
            pstmt.setInt(3, nuevoIdEquipo);
            pstmt.setInt(4, idJugador);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Jugador modificado con exito.");
            } else {
                System.out.println("No se encontro ningun jugador con ese ID.");
            }
        } catch (SQLException e) {
            throw new ExcepcionDeCarga("Error al modificar: " + e.getMessage());
        }
    }


    public void borrarJugador(int idJugador) throws ExcepcionDeCarga {
        String sql = "DELETE FROM jugador WHERE id_jugador = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idJugador);
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Jugador borrado correctamente.");
            } else {
                System.out.println("No se encontro un jugador con ese ID para borrar.");
            }
        } catch (SQLException e) {
            throw new ExcepcionDeCarga("Error al borrar: " + e.getMessage());
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
}