

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Usando Java se pide crear una base de datos llamada listaLibros que contenga
 * una tabla libros con los siguientes atributos:
 * 
 * id: de tipo serial y será la clave primaria
 * titulo: cadena de texto
 * autor: del tipo Autor
 * año_publicacion: tipo entero
 * 
 * Se tendrá que crear también el tipo Autor el cual tiene la siguiente
 * estructura:
 * 
 * nombre_autor: cadena de texto
 * fecha: tipo varchar
 * Se utilizará Java para la creación de dicha tabla
 * 
 * Además, deberá permitir:
 * 
 * Insertar datos
 * Consulta datos
 * Actualizar los datos
 * Eliminación de datos.
 */
public class listaLibros {

    private static Connection conexion = null;
    private static PreparedStatement pStatement = null;
    private static Statement statement = null;

    public listaLibros(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * TODO Pasar la conexion y crear un preparedStatement para pasar los parametros
     * ? ?
     * 
     * @return
     */
    public static String createTables() {
        return "CREATE TABLE if not exists libros (\r\n" + //
                "    id serial PRIMARY KEY,\r\n" + //
                "    titulo varchar(40) NOT NULL,\r\n" + //
                "    autor Autor NOT NULL,\r\n" + //
                "    año_publicacion  integer\r\n" + //
                ")";
    }

    public static String createAutor() {
        return "CREATE TYPE Autor as (nombre_autor text,fecha varchar) ";
    }

    public static void insertarLibro(int id, String titulo, String nombre_autor, String fecha_autor,
            int ano_publicacion) {
        String query = "INSERT INTO libros VALUES (?,?,ROW(?,?),?)";
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setInt(1, id);
            pStatement.setString(2, titulo);
            pStatement.setString(3, nombre_autor);
            pStatement.setString(4, fecha_autor);
            pStatement.setInt(5, ano_publicacion);

            int filasAfectadas = pStatement.executeUpdate();
            System.out.println("filas afectadas  = " + filasAfectadas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean buscarLibro(int id) {
        String query = " SELECT * FROM libros l where l.id=?";
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();

            int numCols = rs.getMetaData().getColumnCount();
            if (numCols > 0) {
                while (rs.next()) {
                    for (int i = 1; i <= numCols; i++) {
                        System.out.print(rs.getString(i) + " ");
                    }
                    System.out.println();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void actualizarLibro(int id, int id2, String titulo2, String nombre_autor2, String fecha_autor2,
            int ano_publicacion2) {
        if (buscarLibro(id)) {
            String query = "UPDATE libros SET id = ?, titulo = ?, autor.nombre_autor = ?, autor.fecha = ?, año_publicacion = ? WHERE id = ?";
            try {
                pStatement = conexion.prepareStatement(query);
                pStatement.setInt(1, id2);
                pStatement.setString(2, titulo2);
                pStatement.setString(3, nombre_autor2);
                pStatement.setString(4, fecha_autor2);
                pStatement.setInt(5, ano_publicacion2);
                pStatement.setInt(6, id);
                int filasAfectadas = pStatement.executeUpdate();
                System.out.println("filas afectadas  = " + filasAfectadas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void eliminarLibro(int id) {
        String query = "DELETE FROM libros where id=?";
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setInt(1, id);
            int filasAfectadas = pStatement.executeUpdate();

            System.out.println("filas afectadas  = " + filasAfectadas);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
