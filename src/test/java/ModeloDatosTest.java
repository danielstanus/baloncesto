import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;

public class ModeloDatosTest { 
     
    private ModeloDatos modeloDatos;
    private Map<String, Integer> baseDeDatosPrueba;

    @BeforeEach
    public void setUp() {
        modeloDatos = new ModeloDatos(); 
        baseDeDatosPrueba = new HashMap<>();

        modeloDatos.abrirConexion();

        System.out.println("111111111 -- setUp FINISHED"); 
    }


    @Test 
    public void testExisteJugador() { 
        System.out.println("Prueba de existeJugador"); 
        String nombre = ""; 


        modeloDatos = new ModeloDatos(); 
        baseDeDatosPrueba = new HashMap<>();

        modeloDatos.abrirConexion();

        boolean expResult = false; 
        boolean result = modeloDatos.existeJugador(nombre); 
        assertEquals(expResult, result); 
        //fail("Fallo forzado."); 
    } 


    @Test 
    public void testActualizarJugador() { 
        System.out.println("Iniciando prueba de actualizarJugador");

        modeloDatos = new ModeloDatos(); 
        baseDeDatosPrueba = new HashMap<>();

        modeloDatos.abrirConexion();


        // Datos de prueba
        String nombreJugador = "Rudy";
        baseDeDatosPrueba.put(nombreJugador, 0); 

        modeloDatos.actualizarJugador(nombreJugador);

        // Verificar que los votos del jugador se han incrementado en 1
        assertEquals(Integer.valueOf(1), baseDeDatosPrueba.get(nombreJugador), "Los votos del jugador no se incrementaron correctamente");

        System.out.println("Prueba de actualizarJugador completada con éxito");


    } 

}