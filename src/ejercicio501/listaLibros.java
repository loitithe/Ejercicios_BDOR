package ejercicio501;

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

    public static String insertarLibro(int id, String titulo, String nombre_autor, String fecha_autor,
            int ano_publicacion) {
        String query = String.format("INSERT INTO libros VALUES (%d,'%s',ROW('%s','%s'),%d)", id, titulo,
                nombre_autor,
                fecha_autor, ano_publicacion);
        return query;

    }

    public static String buscarLibro(int id) {
        String query = String.format(" SELECT * FROM libros l where l.id=%d", id);
        return query;
    }
}
