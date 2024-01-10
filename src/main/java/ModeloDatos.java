import java.sql.*;
import java.util.logging.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


public class ModeloDatos {

    private static final Logger LOGGER = Logger.getLogger(ModeloDatos.class.getName());

    private Connection con;
    private PreparedStatement set;
    private ResultSet rs;
    private String MensajeError = "El error es: ";

    public void abrirConexion() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Usar variables de entorno o valores por defecto
            String dbHost = System.getenv("DATABASE_HOST") != null ? System.getenv("DATABASE_HOST") : "jdbc:mysql://localhost";
            String dbPort = System.getenv("DATABASE_PORT") != null ? System.getenv("DATABASE_PORT") : "3306";
            String dbName = System.getenv("DATABASE_NAME") != null ? System.getenv("DATABASE_NAME") : "baloncesto";
            String dbUser = System.getenv("DATABASE_USER") != null ? System.getenv("DATABASE_USER") : "usuario";
            String dbPass = System.getenv("DATABASE_PASS") != null ? System.getenv("DATABASE_PASS") : "clave";


            String url = dbHost + ":" + dbPort + "/" + dbName;
            
            con = DriverManager.getConnection(url, dbUser, dbPass);
            LOGGER.info("Conexion establecida con exito.");

        } catch (Exception e) {
            LOGGER.severe("No se ha podido conectar.");
            LOGGER.severe(MensajeError + e.getMessage());
        }
    }


    public boolean existeJugador(String nombre) {
        boolean existe = false;
        try {
            set = con.prepareStatement("SELECT * FROM Jugadores WHERE Nombre = ?");
            set.setString(1, nombre);
            rs = set.executeQuery();
            existe = rs.next();
        } catch (Exception e) {
            LOGGER.severe("No lee de la tabla");
            LOGGER.severe(MensajeError + e.getMessage());
        } finally {
            cerrarRecursos();
        }
        return existe;
    }

    public void actualizarJugador(String nombre) {
        try {
            set = con.prepareStatement("UPDATE Jugadores SET votos = votos + 1 WHERE nombre LIKE ?");
            set.setString(1, "%" + nombre + "%");
            set.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe("No modifica la tabla");
            LOGGER.severe(MensajeError + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }

    public void resetVotosJugadores() {
        try {
            set = con.prepareStatement("UPDATE Jugadores SET votos = 0");
            set.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe("No resetea la tabla de Jugadores");
            LOGGER.severe(MensajeError + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }

    public void insertarJugador(String nombre) {
        try {
            set = con.prepareStatement("INSERT INTO Jugadores (nombre, votos) VALUES (?, 1)");
            set.setString(1, nombre);
            set.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe("No inserta en la tabla");
            LOGGER.severe(MensajeError + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }

    public void cerrarConexion() {
        try {
            if (con != null) {
                con.close();
                LOGGER.info("Conexión cerrada con éxito.");
            }
        } catch (Exception e) {
            LOGGER.severe("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    private void cerrarRecursos() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (set != null) {
                set.close();
            }
        } catch (SQLException e) {
            LOGGER.warning("Error al cerrar recursos: " + e.getMessage());
        }
    }


    public List<Map<String, Object>> obtenerVotosJugadores() {
        List<Map<String, Object>> votos = new ArrayList<>();
        try {
            set = con.prepareStatement("SELECT Nombre, Votos FROM Jugadores");
            rs = set.executeQuery();
            while (rs.next()) {
                Map<String, Object> voto = new HashMap<>();
                voto.put("Nombre", rs.getString("Nombre"));
                voto.put("Votos", rs.getInt("Votos"));
                votos.add(voto);
            }
        } catch (Exception e) {
            LOGGER.severe("No se pudo obtener los votos de los jugadores");
            LOGGER.severe(MensajeError + e.getMessage());
        }finally {
            cerrarRecursos();
        }
        return votos;
    }


}
