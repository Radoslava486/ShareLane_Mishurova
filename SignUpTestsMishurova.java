import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignUpTestsMishurova {
    private WebDriver driver;



    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
    }

    @AfterClass
    public void close() {
        driver.quit();
    }


    @Test
    public void zipCodeTestEmptyValues() {
        driver.findElement(By.name("zip_code")).sendKeys("");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(), "#1");

    }

    @Test
    public void zipCodeTestLettersInZipCode() {
        driver.findElement(By.name("zip_code")).sendKeys("k");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(),
                "#2");

    }

    @Test
    public void signUpTestPositive() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed());

        driver.findElement(By.name("first_name")).sendKeys("Rada");
        driver.findElement(By.name("email")).sendKeys("mishurova486@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");

        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        Assert.assertTrue(driver.findElement(By.className("confirmation_message")).isDisplayed(), "#3");
    }


    @Test
    public void signUpTestNegativeEmptyValues() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed());

        driver.findElement(By.name("first_name")).sendKeys("");
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.name("password1")).sendKeys("");
        driver.findElement(By.name("password2")).sendKeys("");

        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        Assert.assertTrue(driver.findElement(By.cssSelector("[value='Register']")).isDisplayed(), "#4");
    }


    @Test
    public void signUpTestNegativeInappropriateEmail() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed());

        driver.findElement(By.name("first_name")).sendKeys("Rada");
        driver.findElement(By.name("email")).sendKeys("жужужу");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");

        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "#5");

    }


    @Test
    public void searchForBookByName() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed());

        driver.findElement(By.name("first_name")).sendKeys("Rada");
        driver.findElement(By.name("email")).sendKeys("mishurova486@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");

        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        driver.findElement(By.cssSelector("[type ='text']")).sendKeys("War and Peace");
        WebElement searchButton = driver.findElement(By.cssSelector("[value='Search']"));
        searchButton.click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='War and Peace ']")).isDisplayed(), " Relevant books should be displayed " );

    }

    @Test
    public void searchForBookByAuthor() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed());

        driver.findElement(By.name("first_name")).sendKeys("Rada");
        driver.findElement(By.name("email")).sendKeys("mishurova486@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        driver.findElement(By.name("keyword")).sendKeys("Leo Tolstoy");
        WebElement searchButton = driver.findElement(By.cssSelector("[value='Search']"));
        searchButton.click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Leo Tolstoy']")).isDisplayed(), " Relevant books should be displayed " );
    }


}
