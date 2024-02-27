import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cursos {
    private static Connection conexion = null;
    private static PreparedStatement pStatement = null;
    private static Statement statement = null;

    public Cursos(Connection conexion) {
        this.conexion = conexion;
    }

    private final String createTypePersona = "CREATE TYPE Persona As(f1 text,f2 int)";

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
}
