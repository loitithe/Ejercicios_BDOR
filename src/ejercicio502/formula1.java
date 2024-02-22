package ejercicio502;

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
