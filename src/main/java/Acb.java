import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            handleVerVotos(req, res);
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

	private void handleVerVotos(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        List<VotoJugador> votos = bd.obtenerTodosLosVotos();
        
        // Genera la tabla HTML con los votos
        StringBuilder tablaHTML = new StringBuilder();
        tablaHTML.append("<table border='1'>");
        tablaHTML.append("<thead>");
        tablaHTML.append("<tr><th>Nombre del Jugador</th><th>Votos</th></tr>");
        tablaHTML.append("</thead>");
        tablaHTML.append("<tbody>");
    
        for (VotoJugador voto : votos) {
            tablaHTML.append("<tr>");
            tablaHTML.append("<td>").append(voto.getNombre()).append("</td>");
            tablaHTML.append("<td>").append(voto.getVotos()).append("</td>");
            tablaHTML.append("</tr>");
        }
    
        tablaHTML.append("</tbody>");
        tablaHTML.append("</table>");
    
        // Establece el contenido de la tabla en el atributo de la solicitud
        req.setAttribute("tablaVotos", tablaHTML.toString());
        
        // Redirecciona a una página JSP para mostrar la tabla
        req.getRequestDispatcher("VerVotos.jsp").forward(req, res);
    }


    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
