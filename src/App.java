import java.sql.*;
import java.util.Scanner;

import ejercicio501.listaLibros;

public class App {
    private static Scanner sc;
    private static Connection conexion = null;
    private static Statement stmt = null;

    public static void main(String[] args) throws Exception {

        sc = new Scanner(System.in);
        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/listaLibros", "postgres",
                    "abc123");
            conexion.setAutoCommit(false);
            stmt = conexion.createStatement();
            menu();
            // stmt.executeUpdate(listaLibros.createAutor());
            // stmt.executeUpdate(listaLibros.createTables());
            stmt.close();
            conexion.commit();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void menu() {
        int opt = pedirInt("1. Insertar \n2. Consultar\n3. Actualizar\n4. Eliminar");
        switch (opt) {
            case 1:
                int id = pedirInt("Id libro :");
                String titulo = pedirString("Titulo de libro :");
                String nombre_autor = pedirString("Nombre del autor :");
                String fecha_autor = pedirString("Introduce la fecha autor :");
                int ano_publicacion = pedirInt("Fecha publicacion :");
                try {
                    stmt.executeUpdate(
                            listaLibros.insertarLibro(id, titulo, nombre_autor, fecha_autor, ano_publicacion));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                int buscarId = pedirInt("Id libro :");
                try {
                    ResultSet rs = stmt.executeQuery(listaLibros.buscarLibro(buscarId));
                    int numCols = rs.getMetaData().getColumnCount();
                    while (rs.next()) {
                        for (int i = 1; i <= numCols; i++) {
                            System.out.print(rs.getString(i) + " ");
                        }
                        System.out.println();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 3:

                break;
            case 4:

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
