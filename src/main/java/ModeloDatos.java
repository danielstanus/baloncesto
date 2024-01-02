import java.sql.*;

public class ModeloDatos {

    private Connection con;
    private PreparedStatement set;
    private ResultSet rs;

    public void abrirConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Usar variables de entorno
            String dbHost = System.getenv("DATABASE_HOST");
            String dbPort = System.getenv("DATABASE_PORT");
            String dbName = System.getenv("DATABASE_NAME");
            String dbUser = System.getenv("DATABASE_USER");
            String dbPass = System.getenv("DATABASE_PASS");

            String url = dbHost + ":" + dbPort + "/" + dbName;
            con = DriverManager.getConnection(url, dbUser, dbPass);

        } catch (Exception e) {
            System.out.println("No se ha podido conectar");
            System.out.println("El error es: " + e.getMessage());
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
            System.out.println("No lee de la tabla");
            System.out.println("El error es: " + e.getMessage());
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
            System.out.println("No modifica la tabla");
            System.out.println("El error es: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }

    public void resetVotosJugadores() {
        try {
            set = con.prepareStatement("UPDATE Jugadores SET votos = 0");
            set.executeUpdate();
        } catch (Exception e) {
            System.out.println("No modifica la tabla");
            System.out.println("El error es: " + e.getMessage());
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
            System.out.println("No inserta en la tabla");
            System.out.println("El error es: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }

    public void cerrarConexion() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
    }
}
