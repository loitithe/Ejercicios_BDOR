import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;

public class Cursos {
    private static Connection conexion = null;
    private static PreparedStatement pStatement = null;
    private static Statement statement = null;

    public Cursos(Connection conexion) {
        this.conexion = conexion;
    }

    private final String createTypePersona = "CREATE TYPE Persona As(nombre text,edad int)";

    private final String createTypeEstudiante = "CREATE TYPE Estudiante As(matricula text,carrera text)";
    private final String createTypeProfesor = "CREATE TYPE Profesor As(cedula text,departamento text)";

    private final String createEstudiantes = "CREATE TABLE if not exits Estudiantes (estudiante_id serial PRIMARY KEY,datos_personales Persona,estudiante_info Estudiante)";

    private final String createCurso = "CREATE TABLE if not exits Curso (curso_id serial PRIMARY KEY,nombre varchar)";

    private final String createProfesores = "CREATE TABLE if not exits Profesores(profesor_id serial PRIMARY_KEY,datos_personales Persona,profesor_info Profesor),curso_id REFERENCES Curso(curso_id)";

    private final String createInscripciones = "CREATE TABLE if not exits Inscripciones (inscripcion_id serial PRIMARY_KEY,estudiante_id REFERENCES Estudiante(matricula),curso_id REFERENCES Curso(curso_id))";

    public static void insertarEstudiante(String nombre, int edad, String matricula, String carrera) {

        String query = "INSERT INTO objetos.Estudiantes(datos_personales, estudiante_info) values (ROW(?,?), ROW(?,?))";
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setString(1, nombre);
            pStatement.setInt(2, edad);
            pStatement.setString(3, matricula);
            pStatement.setString(4, carrera);

            int filasAfectadas = pStatement.executeUpdate();
            System.out.println("filas afectadas  = " + filasAfectadas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertarProfesor(String nombre, int edad, String cedula, String departamento) {
        String query = "INSERT INTO objetos.Profesores(datos_personales,profesor_info) values(ROW(?,?),ROW(?,?))";
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setString(1, nombre);
            pStatement.setInt(2, edad);
            pStatement.setString(3, cedula);
            pStatement.setString(4, departamento);

            int filasAfectadas = pStatement.executeUpdate();
            System.out.println("filas afectadas  = " + filasAfectadas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertarCurso(String nombre) {
        String query = "INSERT INTO objetos.Curso(nombre) values(?)";
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setString(1, nombre);
            int filasAfectadas = pStatement.executeUpdate();
            System.out.println("filas afectadas  = " + filasAfectadas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void consultar(int pedirInt, int tabla) {
        String query = "";
        switch (tabla) {
            case 1:
                query = "SELECT * FROM objetos.Estudiantes where estudiante_id=? ";
                break;
            case 2:
                query = "SELECT * FROM objetos.Profesores where profesor_id=? ";
                break;
            case 3:
                query = "SELECT * FROM objetos.Curso where curso_id=? ";
                break;
            default:
                break;
        }
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setInt(1, pedirInt);
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

    public void actualizarCurso(int id, String nombre) {
        String query = "UPDATE objetos.curso set nombre=? where curso_id=?";
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setString(1, nombre);
            pStatement.setInt(2, id);

            int numCols = pStatement.executeUpdate();
            System.out.println("filas afectadas  = " + numCols);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(int id, int tabla, String nombre, int edad, String t1, String t2) {
        String query = "";
        switch (tabla) {
            case 1:
                query = "UPDATE objetos.estudiantes set datos_personales.nombre=?,datos_personales.edad=?,estudiante_info.matricula=?,estudiante_info.carrera=? where estudiante_id=?";
                break;
            case 2:
                query = "UPDATE objetos.profesores set datos_personales.nombre=?,datos_personales.edad=?,profesor.cedula=?,profesores.matricula=? where profesor_id=?";
                break;

        }
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setString(1, nombre);
            pStatement.setInt(2, edad);
            pStatement.setString(3, t1);
            pStatement.setString(4, t2);
            pStatement.setInt(5, id);

            int numCols = pStatement.executeUpdate();
            System.out.println("Filas afectadas = " + numCols);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
