import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldTest() {
        driver.findElement(By.cssSelector("[data-test-id ='name'] input")).sendKeys("Иван Павлов");
        driver.findElement(By.cssSelector("[data-test-id ='phone'] input")).sendKeys("+79270000000");
        driver.findElement(By.cssSelector("[data-test-id ='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id = order-success]")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTest2Names() {
        driver.findElement(By.cssSelector("[data-test-id ='name'] input")).sendKeys("Иван-Алексей Павлов");
        driver.findElement(By.cssSelector("[data-test-id ='phone'] input")).sendKeys("+79270000000");
        driver.findElement(By.cssSelector("[data-test-id ='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id = order-success]")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

}