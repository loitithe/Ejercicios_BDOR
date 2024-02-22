
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ejercicio 502
 * Usando Java como lenguaje de programación realiza las siguientes consultas en
 * la siguiente base de datos PostgreSQL:
 * 
 * Listar todos los equipos y sus directores
 * Obtener los pilotos y sus equipos actuales
 * Resultados de una carrera específica
 * Piloto más viejo
 * Número de victorias por equipo
 */
public class formula1 {

    private static Connection conexion = null;
    private static PreparedStatement pStatement = null;
    private static Statement statement = null;

    public formula1(Connection conexion) {
        this.conexion = conexion;
    }

    public static void listarEquipos() {
        String query = "SELECT * FROM equipos";
        try {
            pStatement = conexion.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            int numCols = rs.getMetaData().getColumnCount();
            if (numCols > 0) {
                while (rs.next()) {
                    for (int i = 1; i <= numCols; i++) {
                        System.out.print(rs.getString(i) + " ");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarPilotos() {
        String query = "SELECT * FROM pilotos";
        try {
            pStatement = conexion.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            int numCols = rs.getMetaData().getColumnCount();
            if (numCols > 0) {
                while (rs.next()) {
                    for (int i = 1; i <= numCols; i++) {
                        System.out.print(rs.getString(i) + " ");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resultadoCarrera(int resultado_id) {
        String query = "SELECT * FROM resultados where resultado_id=?";
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setInt(1, resultado_id);
            ResultSet rs = pStatement.executeQuery();
            int numCols = rs.getMetaData().getColumnCount();
            if (numCols > 0) {
                while (rs.next()) {
                    for (int i = 1; i <= numCols; i++) {
                        System.out.print(rs.getString(i) + " ");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void pilotoViejo() {
        String query = "SELECT * FROM pilotos ORDER BY fecha_nacimiento LIMIT 1";
        try {
            pStatement = conexion.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            int numCols = rs.getMetaData().getColumnCount();
            if (numCols > 0) {
                while (rs.next()) {
                    for (int i = 1; i <= numCols; i++) {
                        System.out.print(rs.getString(i) + " ");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void victorias_equipo() {
        String query = "SELECT equipos.nombre AS \"Equipo\", COUNT(resultados.resultado_id) AS \"Victorias\" " +
                "FROM resultados " +
                "INNER JOIN pilotos ON resultados.piloto_id = pilotos.piloto_id " +
                "INNER JOIN equipos ON pilotos.equipo_id = equipos.equipo_id " +
                "WHERE resultados.posicion = 1 " +
                "GROUP BY equipos.nombre";
        try {
            pStatement = conexion.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            int numCols = rs.getMetaData().getColumnCount();
            if (numCols > 0) {
                while (rs.next()) {
                    for (int i = 1; i <= numCols; i++) {
                        System.out.print(rs.getString(i) + " ");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
/**
 * -- Crear una base de datos llamada "formula1"
 * CREATE DATABASE formula1;
 * 
 * -- Crear una tabla para los equipos
 * CREATE TABLE equipos (
 * equipo_id serial PRIMARY KEY,
 * nombre VARCHAR(255),
 * sede VARCHAR(255),
 * director VARCHAR(255)
 * );
 * 
 * -- Crear una tabla para los pilotos
 * CREATE TABLE pilotos (
 * piloto_id serial PRIMARY KEY,
 * nombre VARCHAR(255),
 * nacionalidad VARCHAR(50),
 * equipo_id INT REFERENCES equipos(equipo_id),
 * fecha_nacimiento DATE
 * );
 * 
 * -- Crear una tabla para las carreras
 * CREATE TABLE carreras (
 * carrera_id serial PRIMARY KEY,
 * nombre VARCHAR(255),
 * fecha DATE,
 * pais VARCHAR(100)
 * );
 * 
 * -- Crear una tabla para los resultados de las carreras
 * CREATE TABLE resultados (
 * resultado_id serial PRIMARY KEY,
 * carrera_id INT REFERENCES carreras(carrera_id),
 * piloto_id INT REFERENCES pilotos(piloto_id),
 * posicion INT,
 * tiempo VARCHAR(50)
 * );
 * 
 * -- Insertar datos de ejemplo en la tabla equipos
 * INSERT INTO equipos (nombre, sede, director) VALUES
 * ('Mercedes', 'Brackley, Reino Unido', 'Toto Wolff'),
 * ('Red Bull Racing', 'Milton Keynes, Reino Unido', 'Christian Horner');
 * 
 * -- Insertar datos de ejemplo en la tabla pilotos
 * INSERT INTO pilotos (nombre, nacionalidad, equipo_id, fecha_nacimiento)
 * VALUES
 * ('Lewis Hamilton', 'Reino Unido', 1, '1985-01-07'),
 * ('Max Verstappen', 'Países Bajos', 2, '1997-09-30');
 * 
 * -- Insertar datos de ejemplo en la tabla carreras
 * INSERT INTO carreras (nombre, fecha, pais) VALUES
 * ('Gran Premio de Australia', '2023-03-20', 'Australia'),
 * ('Gran Premio de España', '2023-05-14', 'España');
 * 
 * -- Insertar datos de ejemplo en la tabla resultados
 * INSERT INTO resultados (carrera_id, piloto_id, posicion, tiempo) VALUES
 * (1, 1, 1, '1:23:45'),
 * (1, 2, 2, '1:23:55'),
 * (2, 1, 1, '1:30:00'),
 * (2, 2, 2, '1:30:10');
 * 
 */