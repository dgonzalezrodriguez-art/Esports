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
public List<Jugador> obtenerTodosLosJugadores() throws ExcepcionDeCarga{
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
}}
