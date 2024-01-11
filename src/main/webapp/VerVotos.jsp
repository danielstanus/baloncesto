
<%@ page import="src/main/java/VotoJugador.java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Votos de los jugadores de la liga ACB</title>
        <link href="estilos.css" rel="stylesheet" type="text/css" /> 
    </head>
    <body  class="votos" >
        <h2>Votos de los jugadores de la liga ACB</h2>
       
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Votos</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<VotoJugador> votos = (List<VotoJugador>) request.getAttribute("listaVotos");
                for (VotoJugador voto : votos) { 
                %>
                    <tr>
                        <td><%= voto.getNombre() %></td>
                        <td><%= voto.getVotos() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>


        <br>
        <br> <a href="index.html"> Ir al comienzo</a>
    </body>
</html>



