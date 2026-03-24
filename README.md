# Proyecto eSports - Gestión de Torneos y Equipos

## Descripción General
Esta es una aplicación de consola en Java desarrollada para el módulo de Programación (1º DAW). Permite gestionar la información de una liga de eSports almacenada en una base de datos relacional. Soporta operaciones completas CRUD (Crear, Leer, Actualizar, Borrar) sobre equipos, jugadores y torneos.

## Integrantes
* **[Gonzalo Díaz Fernández]**
* **[David González Rodriguez]**

## Temática Elegida
**eSports (Deportes Electrónicos)**. La aplicación simula la gestión de un ecosistema competitivo, donde los jugadores pertenecen a equipos, y los equipos participan en diferentes torneos.

## Modelo de Tablas Utilizado
La base de datos relacional se compone de las siguientes tablas principales (cumpliendo con el requisito del prefijo `ZA_`):
* `ZA_EQUIPOS`: Almacena la información de los clubes de eSports.
* `ZA_JUGADORES`: Almacena los datos de los pro-players. (Relación **1:N** con Equipos: Un equipo tiene muchos jugadores).
* `ZA_TORNEOS`: Almacena la información de las competiciones.
* `ZA_EQUIPOS_TORNEOS`: Tabla intermedia para resolver la relación **N:M** (Muchos equipos participan en muchos torneos).
* `ZA_JUEGO`: Almacena todos los juegos que tienen eSports.

## Tecnologías y Arquitectura Usadas
* **Lenguaje:** Java 
* **Acceso a Datos:** Opción A (JDBC)
* **Base de Datos:** MariaDB / MySQL
* **Arquitectura destacada:**
  * Uso de consultas parametrizadas (`PreparedStatement`).
  * Paquete de utilidades de conexión aislado en la carpeta `dao`.
  * Gestión de errores mediante la excepción personalizada `ExcepcionDeCarga`.
  * Todos los modelos implementan el atributo obligatorio `indexador`.
 
## Instrucciones de Ejecución

1. **Base de datos:**
   * Abre tu gestor de base de datos (por ejemplo, DBeaver, phpMyAdmin o consola MySQL).
   * Ejecuta el script de creación incluido en el proyecto (`script_esports.sql`). Este script creará la estructura de tablas y añadirá los datos de prueba iniciales.
2. **Configuración del proyecto:**
   * Clona este repositorio o abre el directorio en tu IDE favorito (IntelliJ IDEA, Eclipse, VSCode).
   * Asegúrate de que el conector JDBC (`mysql-connector-java` o `mariadb-java-client`) está añadido al `pom.xml` o al Build Path.
   * Si es necesario, modifica las credenciales de conexión (URL, usuario, contraseña) en la clase gestora de conexiones dentro de la carpeta `dao` y en el archivo `ConexionDB`.
3. **Ejecución:**
   * Ejecuta la clase `Main.java` (que sirve como UI principal).
   * Navega por el menú interactivo introduciendo los números de las opciones correspondientes para dar de alta, listar, modificar o eliminar datos de la liga de eSports.
  
