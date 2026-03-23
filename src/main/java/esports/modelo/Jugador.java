package esports.modelo;

public class Jugador {
    private int idJugador;
    private String nombre;
    private String rol;
    private int idEquipo;


    public Jugador(int idJugador, String nombre, String rol, int idEquipo) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.rol = rol;
        this.idEquipo = idEquipo;
    }
    public int getIdJugador() {
        return idJugador;
    }
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public int getIdEquipo() {
        return idEquipo;
    }
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    @Override
    public String toString() {
        return "ID: " + idJugador + " | Nickname: " + nombre + " | Rol: " + rol + " | ID Equipo: " + idEquipo;
    }
}
