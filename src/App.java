import java.sql.*;
import java.util.Scanner;

import ejercicio501.listaLibros;
import ejercicio502.formula1;

public class App {
    private static Scanner sc;
    private static Connection conexion = null;
    private static Statement stmt = null;
    private static listaLibros listaLibros;
    private static formula1 formula1;

    public static void main(String[] args) throws Exception {

        sc = new Scanner(System.in);
        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/formula1", "postgres",
                    "abc123");
            conexion.setAutoCommit(false);
            stmt = conexion.createStatement();
            // listaLibros = new listaLibros(conexion);
            // ejercicio501_menu();
            // stmt.executeUpdate(listaLibros.createAutor());
            // stmt.executeUpdate(listaLibros.createTables());
            formula1 = new formula1(conexion);
            // formula1.listarEquipos();
            // ormula1.listarPilotos();
            // formula1.resultadoCarrera(1);
            // formula1.pilotoViejo();
            formula1.victorias_equipo();

            stmt.close();
            conexion.commit();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ejercicio501_menu() {
        int opt = pedirInt("1. Insertar \n2. Consultar\n3. Actualizar\n4. Eliminar");
        switch (opt) {
            case 1:
                int id = pedirInt("Id libro :");
                String titulo = pedirString("Titulo de libro :");
                String nombre_autor = pedirString("Nombre del autor :");
                String fecha_autor = pedirString("Introduce la fecha autor :");
                int ano_publicacion = pedirInt("Fecha publicacion :");
                listaLibros.insertarLibro(id, titulo, nombre_autor, fecha_autor, ano_publicacion);

                break;
            case 2:
                int buscarId = pedirInt("Id libro :");
                listaLibros.buscarLibro(buscarId);
                break;
            case 3:
                int id1 = pedirInt("Id libro a actualizar :");
                int id2 = pedirInt("nuevo id libro :");
                if (listaLibros.buscarLibro(id1)) {

                    String titulo2 = pedirString("nuevo Titulo de libro :");
                    String nombre_autor2 = pedirString("nuevo Nombre del autor :");
                    String fecha_autor2 = pedirString("nueva fecha autor :");
                    int ano_publicacion2 = pedirInt("nueva Fecha publicacion :");
                    listaLibros.actualizarLibro(id1, id2, titulo2, nombre_autor2, fecha_autor2, ano_publicacion2);
                }
                break;
            case 4:
                int id_eliminar = pedirInt("Id libro :");
                if (listaLibros.buscarLibro(id_eliminar)) {
                    listaLibros.eliminarLibro(id_eliminar);
                }
                break;

            default:
                break;
        }

    }

    public static String pedirString(String mensaje) {
        System.out.println(mensaje);
        String entrada = "";
        do {
            try {
                entrada = sc.next();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un string");
            }
        } while (entrada.equals(""));

        return entrada;
    }

    public static int pedirInt(String mensaje) {
        System.out.println(mensaje);
        int entrada = -1;
        do {
            try {
                entrada = sc.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Debes introducir un integer");
            }
        } while (entrada < 0);

        return entrada;
    }
}
