import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ejercicio 503
 * Usando Java se pide realizar las siguientes funcionalidades usando como
 * referencia la siguiente base de datos sobre pokemons
 * 
 * Las funciones a implementar será aquellas que permitan:
 * 
 * Insertar un nuevo pokemon.
 * Modificar un pokemon.
 * Eliminar un pokemon.
 */
public class Pokemons {
    private static Connection conexion = null;
    private static PreparedStatement pStatement = null;
    private static Statement statement = null;

    public Pokemons(Connection conexion) {
        this.conexion = conexion;
    }

    public static void insertarPokemon(String nombre, String tipo, int nivel) {
        String query = "INSERT INTO objetos.Pokemons (pokemon) VALUES (ROW(?, ?, ?))";
        try {
            pStatement = conexion.prepareStatement(query);
            pStatement.setString(1, nombre);
            pStatement.setString(2, tipo);
            pStatement.setInt(3, nivel);
            int filasAfectadas = pStatement.executeUpdate();
            System.out.println("filas afectadas " + filasAfectadas);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean existePokemon(int id) {
        String query = " SELECT * FROM objetos.pokemons p where p.id=?";
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

    public static void modificarPokemon(int id, String nombre, String tipo, int nivel) {
        if (existePokemon(id)) {
            String query = "UPDATE objetos.pokemons SET id = ?, titulo = ?, autor.nombre_autor = ?, autor.fecha = ?, año_publicacion = ? WHERE id = ?";
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

    public static void eliminarPokemon() {

    }
}

/**
 * CREATE SCHEMA objetos;
 * 
 * -- Definir el tipo de datos personalizado "Pokemon"
 * CREATE TYPE objetos.Pokemon AS (
 * nombre VARCHAR(255),
 * tipo VARCHAR(50),
 * nivel INT
 * );
 * 
 * -- Crear una tabla de Pokémon que utiliza el tipo de datos personalizado
 * "Pokemon"
 * CREATE TABLE objetos.Pokemons (
 * id serial PRIMARY KEY,
 * pokemon objetos.Pokemon
 * );
 * 
 * -- Insertar datos de ejemplo
 * INSERT INTO objetos.Pokemons (pokemon)
 * VALUES (ROW('Pikachu', 'Eléctrico', 30));
 * 
 * INSERT INTO objetos.Pokemons (pokemon)
 * VALUES (ROW('Charizard', 'Fuego/Volador', 50));
 */
