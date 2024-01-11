<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Votos de los jugadores de la liga ACB</title>
        <link href="estilos.css" rel="stylesheet" type="text/css" /> 
    </head>
    <body  class="votos" >
        <h1>Votos de los jugadores de la liga ACB</h1>


        <%
            List<Map<String, Object>> votos = (List<Map<String, Object>>) request.getAttribute("listaVotos");
            if (votos != null && !votos.isEmpty()) {
        %>
        <table border="1">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Votos</th>
            </tr>
            </thead>
            <tbody>
            <% for (Map<String, Object> voto : votos) { %>
            <tr>
                <td><%= voto.get("nombre") %></td>
                <td><%= voto.get("votos") %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } else { %>
        <p>No hay votos disponibles.</p>
        <% } %>

        

        <br>
        <br> 
        <a href="index.html"> Ir al comienzo</a>
    </body>
</html>



