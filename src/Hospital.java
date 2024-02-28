import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Hospital {

    private static Connection conexion = null;
    private static PreparedStatement pStatement = null;
    private static Statement statement = null;

    public Hospital(Connection conexion) {
        this.conexion = conexion;
    }

}
