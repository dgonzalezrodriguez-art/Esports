package esports.modelo;

public class Equipo {
    private int idEquipo;
    private String nombre;
    private int idJuego;

    public Equipo(int idEquipo, String nombre, int idJuego) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.idJuego = idJuego;
    }

    public int getIdEquipo() { return idEquipo; }
    public String getNombre() { return nombre; }
    public int getIdJuego() { return idJuego; }
}