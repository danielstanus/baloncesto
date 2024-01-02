
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Acb extends HttpServlet {

    private ModeloDatos bd;

    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
    
        // Verifica qué botón se ha presionado
        if (req.getParameter("btnVotar") != null) {
            String nombreP = (String) req.getParameter("txtNombre");
            String nombre = (String) req.getParameter("R1");
            if ("Otros".equals(nombre)) {
                nombre = (String) req.getParameter("txtOtros");
            }
            if (bd.existeJugador(nombre)) {
                bd.actualizarJugador(nombre);
            } else {
                bd.insertarJugador(nombre);
            }
            s.setAttribute("nombreCliente", nombreP);
            // Llamada a la página jsp que nos da las gracias
            res.sendRedirect(res.encodeRedirectURL("TablaVotos.jsp"));
    
        } else if (req.getParameter("btnResetVotos") != null) {
            // Lógica para poner los votos a cero
            bd.resetVotosJugadores();
            res.sendRedirect(res.encodeRedirectURL("TablaVotos.jsp"));
    
        } 
    }

    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
