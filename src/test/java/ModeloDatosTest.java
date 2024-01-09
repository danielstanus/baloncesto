import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;

public class ModeloDatosTest { 
     
    @Test 
    public void testExisteJugador() { 
        System.out.println("Prueba de existeJugador"); 
        String nombre = "Pepito"; 

        ModeloDatos modeloDatos = new ModeloDatos(); 
        modeloDatos.abrirConexion();

        boolean expResult = false; 
        boolean result = modeloDatos.existeJugador(nombre); 
        assertEquals(expResult, result); 
    } 


  
    public void testActualizarJugador() { 
        System.out.println("Iniciando prueba de actualizarJugador");

        ModeloDatos modeloDatos = new ModeloDatos(); 
        modeloDatos.abrirConexion();

        Map<String, Integer> baseDeDatosPrueba = new HashMap<>();

        // Datos de prueba
        String nombreJugador = "Rudy";
        baseDeDatosPrueba.put(nombreJugador, 0); 

        modeloDatos.actualizarJugador(nombreJugador);

        // Verificar que los votos del jugador se han incrementado en 1
        assertEquals(Integer.valueOf(1), baseDeDatosPrueba.get(nombreJugador), "Los votos del jugador no se incrementaron correctamente");

        System.out.println("Prueba de actualizarJugador completada con éxito");


    } 

}