
<%@ page import="VotoJugador" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Votos de los jugadores de la liga ACB</title>
        <link href="estilos.css" rel="stylesheet" type="text/css" /> 
    </head>
    <body  class="votos" >
        <h2>Votos de los jugadores de la liga ACB</h2>
       


            <%
                session.getAttribute("listaVotos");
            %>


        <br>
        <br> <a href="index.html"> Ir al comienzo</a>
    </body>
</html>



