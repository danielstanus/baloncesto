<%@ page import="ModeloDatos.VotoJugador" %>
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
                    <th>Nombre del Jugador</th>
                    <th>Votos</th>
                </tr>
            </thead>
            <tbody>
                <!-- Aquí insertaremos las filas de la tabla dinámicamente -->
                <% 
                List<Map<String, Object>> votos = ModeloDatos.obtenerVotosJugadores();
                for (Map<String, Object> voto : votos) { 
                %>
                    <tr>
                        <td><%= voto.get("Nombre") %></td>
                        <td><%= voto.get("Votos") %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>


        <br>
        <br> <a href="index.html"> Ir al comienzo</a>
    </body>
</html>



