import java.sql.*;
import java.util.Scanner;

public class App {
    private static Scanner sc;
    private static Connection conexion = null;
    private static Statement stmt = null;
    private static listaLibros listaLibros;
    private static formula1 formula1;
    private static Pokemons pokemons;
    private static Cursos cursos;

    public static void main(String[] args) throws Exception {

        sc = new Scanner(System.in);
        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Curso", "postgres",
                    "abc123");
            conexion.setAutoCommit(false);
            stmt = conexion.createStatement();
            // listaLibros = new listaLibros(conexion);
            // ejercicio501_menu();
            // stmt.executeUpdate(listaLibros.createAutor());
            // stmt.executeUpdate(listaLibros.createTables());
            /**
             * ##### formula 1 ######
             */
            // formula1 = new formula1(conexion);
            // formula1.listarEquipos();
            // formula1.listarPilotos();
            // formula1.resultadoCarrera(1);
            // formula1.pilotoViejo();
            // formula1.victorias_equipo();
            /**
             * ##### pokemons ######
             */
            // pokemons = new Pokemons(conexion);
            // pokemons.insertarPokemon(pedirString("Nombre :"), pedirString("Tipo"),
            // pedirInt("Nivel "));
            // pokemons.existePokemon(1);
            /**
             * ##### cursos ######
             */
            cursos = new Cursos(conexion);
            ejercicio504_menu();
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

    public static void ejercicio504_menu() {
        int opt = pedirInt("1. Insertar \n2. Consultar\n3. Actualizar\n4. Eliminar");
        switch (opt) {
            case 1:
                int opt2 = pedirInt("1. Estudiante\n2. Profesor\n3. Curso");
                switch (opt2) {
                    case 1:
                        cursos.insertarEstudiante(pedirString("Nombre"), pedirInt("edad"), pedirString("Matricula"),
                                pedirString("Carrerra"));
                        break;
                    case 2:
                        cursos.insertarProfesor(pedirString("Nombre"), pedirInt("edad"), pedirString("cedula"),
                                pedirString("Departamento"))1;
                        break;
                    case 3:
                        cursos.insertarCurso(pedirString("Nombre curso: "));
                        break;
                    default:
                        break;
                }
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;

            default:
                break;
        }
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
