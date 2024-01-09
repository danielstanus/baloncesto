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

}