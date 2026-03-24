package esports.dao;

import esports.excepciones.ExcepcionDeCarga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {

    public void insertarEquipo(String nombreEquipo, int idJuego) throws ExcepcionDeCarga {
        String sql = "INSERT INTO equipo(nombre, id_juego) VALUES(?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreEquipo);
            stmt.setInt(2, idJuego);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new ExcepcionDeCarga("No se pudo insertar el equipo: " + e.getMessage());
        }
    }
    public List<String> obtenerEquiposPorJuego(int idJuego) throws ExcepcionDeCarga {
        List<String> equipos = new ArrayList<>();
        String sql = "SELECT nombre, id_equipo FROM equipo WHERE id_juego = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJuego);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                equipos.add(rs.getInt("id_equipo") + " - " + rs.getString("nombre"));
            }
        } catch (Exception e) {
            throw new ExcepcionDeCarga("No se pudieron obtener los equipos: " + e.getMessage());
        }
        return equipos;
    }


    public void asignarEquipoAJuego(int idEquipo, int idJuego) throws ExcepcionDeCarga {
        String sql = "UPDATE equipo SET id_juego = ? WHERE id_equipo = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idJuego);
            stmt.setInt(2, idEquipo);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new ExcepcionDeCarga("No se pudo asignar el equipo al juego: " + e.getMessage());
        }
    }
}
