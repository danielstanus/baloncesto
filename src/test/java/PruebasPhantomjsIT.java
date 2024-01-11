import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PruebasPhantomjsIT {

    private static WebDriver driver = null;
    private String baseUrl = "http://localhost:8080/Baloncesto";


    @Test
    public void tituloIndexTest() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);

        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "/usr/bin/phantomjs"
        );
        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[]{"--web-security=no", "--ignore-ssl-errors=yes"}
        );
        driver = new PhantomJSDriver(caps);
        driver.navigate().to(baseUrl + "/index.html");
        assertEquals(
                "Votacion mejor jugador liga ACB",
                driver.getTitle(),
                "El titulo no es correcto"
        );
        System.out.println(driver.getTitle());
        driver.close();
        driver.quit();
    }


    @Test
    public void pruebaPonerVotosACeroYVerVotos() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);

        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "/usr/bin/phantomjs"
        );
        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[]{"--web-security=no", "--ignore-ssl-errors=yes"}
        );
        driver = new PhantomJSDriver(caps);


        driver.get(baseUrl + "/index.html");

        // Encuentra y pulsa el botón "Poner votos a cero"
        WebElement botonPonerVotosACero = driver.findElement(By.id("btnResetVotos"));
        botonPonerVotosACero.click();

        // Encuentra y pulsa el botón "Ver votos"
        WebElement botonVerVotos = driver.findElement(By.id("btnVerVotos"));
        botonVerVotos.click();

        // Verifica que todos los votos en la página "VerVotos.jsp" son cero
        WebElement tablaVotos = driver.findElement(By.tagName("table"));
        WebElement cuerpoTabla = tablaVotos.findElement(By.tagName("tbody"));
        for (WebElement fila : cuerpoTabla.findElements(By.tagName("tr"))) {
            WebElement celdaVotos = fila.findElements(By.tagName("td")).get(1);
            assertEquals("0", celdaVotos.getText());
        }
    }





}
