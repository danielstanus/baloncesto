import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;

public class ModeloDatosTest {

    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "Rudy";

        ModeloDatos modeloDatos = new ModeloDatos();
        modeloDatos.abrirConexion();

        boolean expResult = true;
        boolean result = modeloDatos.existeJugador(nombre);
        assertEquals(expResult, result, "El jugador no existe y deberia de existir");
    }


    @Test
    public void testActualizarJugador() {
        System.out.println("Iniciando prueba de actualizarJugador");

        ModeloDatos modeloDatos  = new ModeloDatos();
        modeloDatos.abrirConexion();

        String nombreJugador = "Rudy";
        Integer votosJugadorAntes = modeloDatos.obtenerVotosJugador(nombreJugador);

        // Actualizamos los votos de jugador Rudy en 1
        modeloDatos.actualizarJugador(nombreJugador);

        //Obtenemos los votos del jugador despues de actualizar
        Integer votosJugadorDespues= modeloDatos.obtenerVotosJugador(nombreJugador);

        // Verificar que los votos del jugador Rudy han incrementado en 1
        assertEquals(votosJugadorDespues.intValue(), votosJugadorAntes+3,"Los votos del jugador no se incrementaron correctamente");
  
        // Ponemos los votos como lo tenia antes el jugador
        modeloDatos.actualizarVotoJugador(nombreJugador,votosJugadorAntes);

        System.out.println("Prueba de actualizarJugador completada con éxito");
    }


}