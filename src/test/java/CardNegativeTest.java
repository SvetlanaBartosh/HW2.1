import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardNegativeTest {
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
    public void EmptyTest() {
        driver.findElement(By.cssSelector("[data-test-id ='name'] input")).clear();
        driver.findElement(By.cssSelector("[data-test-id ='phone'] input")).clear();
        driver.findElement(By.cssSelector("[data-test-id ='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id = 'name'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invalidNameTest() {
        driver.findElement(By.cssSelector("[data-test-id ='name'] input")).sendKeys("svetlana");
        driver.findElement(By.cssSelector("[data-test-id ='phone'] input")).sendKeys("+79270000000");
        driver.findElement(By.cssSelector("[data-test-id ='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id = 'name'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invalidNameTest2() {
        driver.findElement(By.cssSelector("[data-test-id ='name'] input")).sendKeys("1234");
        driver.findElement(By.cssSelector("[data-test-id ='phone'] input")).sendKeys("+79270000000");
        driver.findElement(By.cssSelector("[data-test-id ='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id = 'name'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invalidNameClear() {
        driver.findElement(By.cssSelector("[data-test-id ='name'] input")).clear();
        driver.findElement(By.cssSelector("[data-test-id ='phone'] input")).sendKeys("+79270000000");
        driver.findElement(By.cssSelector("[data-test-id ='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id = 'name'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invalidPhoneTest() {
        driver.findElement(By.cssSelector("[data-test-id ='name'] input")).sendKeys("Иван Павлов");
        driver.findElement(By.cssSelector("[data-test-id ='phone'] input")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[data-test-id ='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id = 'phone'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invalidPhoneNumberTest() {
        driver.findElement(By.cssSelector("[data-test-id ='name'] input")).sendKeys("Иван Павлов");
        driver.findElement(By.cssSelector("[data-test-id ='phone'] input")).sendKeys("123");
        driver.findElement(By.cssSelector("[data-test-id ='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id = 'phone'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invalidPhoneClear() {
        driver.findElement(By.cssSelector("[data-test-id ='name'] input")).sendKeys("Иван Павлов");
        driver.findElement(By.cssSelector("[data-test-id ='phone'] input")).clear();
        driver.findElement(By.cssSelector("[data-test-id ='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id = 'phone'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void noAgreementTest() {
        driver.findElement(By.cssSelector("[data-test-id ='name'] input")).sendKeys("Иван Павлов");
        driver.findElement(By.cssSelector("[data-test-id ='phone'] input")).sendKeys("+79270000000");
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("[data-test-id = 'agreement'].input_invalid")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }
}
