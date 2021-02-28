package th.ac.ku.atm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTests {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private static WebDriverWait wait;

    @FindBy(id = "inputId")
    private WebElement idField;

    @FindBy(id = "inputPin")
    private WebElement pinField;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/login");
        PageFactory.initElements(driver, this);

    }

    @AfterEach
    public void afterEach() throws InterruptedException {
        Thread.sleep(3000);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    void testLoginCorrectIdPin() {
        idField.sendKeys("1");
        pinField.sendKeys("1111");
        submitButton.click();

        WebElement resultHeader = wait.until(webDriver ->
                webDriver.findElement(By.tagName("h1")));
        assertEquals("Art Bank Accounts", resultHeader.getText());
    }

    @Test
    void testLoginIncorrectIdPin() {
        idField.sendKeys("1");
        pinField.sendKeys("99");
        submitButton.click();

        WebElement resultHeader = wait.until(webDriver ->
                webDriver.findElement(By.tagName("h1")));
        assertEquals("Can't find customer", resultHeader.getText());
    }


}
