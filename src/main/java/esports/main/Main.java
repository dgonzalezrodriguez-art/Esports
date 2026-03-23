package esports.main;

import esports.dao.JugadorDAO;
import esports.dao.EquipoDAO;
import esports.excepciones.ExcepcionDeCarga;
import esports.modelo.Jugador;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JugadorDAO jugadorDAO = new JugadorDAO();
        EquipoDAO equipoDAO = new EquipoDAO(); // <-- Añadimos la herramienta de equipos
        int opcion = 0;

        System.out.println("gestor de esports");
        while (opcion != 7) { // <-- Ahora salimos con el 7
            System.out.println("1. Crear Jugador");
            System.out.println("2. Listar Jugadores");
            System.out.println("3. Modificar un Jugador");
            System.out.println("4. Borrar Jugador");
            System.out.println("5. Ver Jugadores con su Equipo (JOIN)");
            System.out.println("6. Crear Equipo"); // <-- NUEVA OPCION
            System.out.println("7. Salir");
            System.out.print("Elige una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("Nombre: ");
                        String nomb = scanner.nextLine();
                        System.out.print("Rol: ");
                        String rol = scanner.nextLine();
                        System.out.print("ID del Equipo: ");
                        int idEq = Integer.parseInt(scanner.nextLine());
                        jugadorDAO.insertarJugador(new Jugador(0, nomb, rol, idEq));
                        break;
                    case 2:
                        List<Jugador> jugadores = jugadorDAO.obtenerTodosLosJugadores();
                        if (jugadores.isEmpty()) {
                            System.out.println("No hay jugadores registrados.");
                        } else {
                            for (Jugador j : jugadores) {
                                System.out.println(j.toString());
                            }
                        }
                        break;
                    case 3:
                        System.out.print("ID del jugador a modificar: ");
                        int idMod = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nuevo Nombre: ");
                        String nuevoNomb = scanner.nextLine();
                        System.out.print("Nuevo Rol: ");
                        String nuevoRol = scanner.nextLine();
                        System.out.print("ID de Equipo (Introduce el que ya tenia si no cambia): ");
                        int nuevoIdEq = Integer.parseInt(scanner.nextLine());
                        jugadorDAO.modificarJugador(idMod, nuevoNomb, nuevoRol, nuevoIdEq);
                        break;
                    case 4:
                        System.out.print("ID del jugador a borrar: ");
                        int idBorrar = Integer.parseInt(scanner.nextLine());
                        jugadorDAO.borrarJugador(idBorrar);
                        break;
                    case 5:
                        System.out.println("--- JUGADORES Y SUS EQUIPOS (JOIN) ---");
                        List<String> jugadoresConEquipo = jugadorDAO.obtenerJugadoresConEquipo();
                        if (jugadoresConEquipo.isEmpty()) {
                            System.out.println("No hay jugadores asignados a ningun equipo.");
                        } else {
                            for (String info : jugadoresConEquipo) {
                                System.out.println(info);
                            }
                        }
                        System.out.println("--------------------------------------");
                        break;
                    case 6:
                        System.out.println("--- CREAR NUEVO EQUIPO ---");
                        System.out.print("Nombre del equipo (Ej: Team Heretics): ");
                        String nombreEquipo = scanner.nextLine();
                        System.out.print("ID del Juego al que juegan (1=LoL, 2=Valorant, 3=CS2): ");
                        int idJuego = Integer.parseInt(scanner.nextLine());
                        equipoDAO.insertarEquipo(nombreEquipo, idJuego);
                        break;
                }
            } catch (ExcepcionDeCarga e) {
                System.out.println("Error de base de datos: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: Por favor, introduce un numero valido.");
            }
        }
        scanner.close();
    }
}