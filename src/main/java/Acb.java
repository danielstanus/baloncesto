import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class Acb extends HttpServlet {

    private ModeloDatos bd;

    @Override
    public void init() throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        // Verificar qué botón se ha presionado
        if (req.getParameter("btnVotar") != null) {
            handleVotar(req, session, res);
        } else if (req.getParameter("btnResetVotos") != null) {
            handleResetVotos(res);
        }else if (req.getParameter("btnVerVotos") != null) {
            // handleVerVotos(req, res);
            handleVerVotos(req, session, res);
        }
    }

    private void handleVotar(HttpServletRequest req, HttpSession session, HttpServletResponse res) throws IOException {
        String nombreP = req.getParameter("txtNombre");
        String nombre = req.getParameter("R1");

        if ("Otros".equals(nombre)) {
            nombre = req.getParameter("txtOtros");
        }

        if (bd.existeJugador(nombre)) {
            bd.actualizarJugador(nombre);
        } else {
            bd.insertarJugador(nombre);
        }

        session.setAttribute("nombreCliente", nombreP);
        res.sendRedirect(res.encodeRedirectURL("TablaVotos.jsp"));
    }

    private void handleResetVotos(HttpServletResponse res) throws IOException {
        bd.resetVotosJugadores();
        res.sendRedirect(res.encodeRedirectURL("index.html"));
    }


    private void handleVerVotos(HttpServletRequest req,HttpSession session, HttpServletResponse res) throws IOException, ServletException {
        List<Map<String, Object>> votos = bd.obtenerTodosLosVotos();
        // req.setAttribute("listaVotos", votos);
        // req.getRequestDispatcher("VerVotos.jsp").forward(req, res);

        session.setAttribute("listaVotos", votos);
        res.sendRedirect(res.encodeRedirectURL("VerVotos.jsp"));

    }


    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
