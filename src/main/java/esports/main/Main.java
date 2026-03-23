package esports.main;

import esports.dao.JugadorDAO;
import esports.excepciones.ExcepcionDeCarga;
import esports.modelo.Jugador;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JugadorDAO jugadorDAO = new JugadorDAO();
        int opcion = 0;

        System.out.println("gestor de esports");
        while (opcion != 6) {
            System.out.println("1. Crear Jugador");
            System.out.println("2. Listar Jugadores");
            System.out.println("3. Modificar un Jugador");
            System.out.println("4. Borrar Jugador");
            System.out.println("5. Ver Jugadores con su Equipo (JOIN)");
            System.out.println("6. Salir");
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
                        int idEquipo = Integer.parseInt(scanner.nextLine());
                        jugadorDAO.insertarJugador(new Jugador(0, nomb, rol, idEquipo));
                        break;
                    case 2:
                        List<Jugador> jugadores = jugadorDAO.obtenerTodosLosJugadores();
                        for (Jugador j : jugadores) {
                            System.out.println(j.toString());
                        }
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
