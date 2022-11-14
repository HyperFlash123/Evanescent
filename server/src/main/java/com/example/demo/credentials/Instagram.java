import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class Instagram {

	public static void deleteAccount() {
		
		//Retrieving account data from database		
		String username = "evanescenceerasure";
		String password ="GDPRregs1!";
		
		//declaring the specific executable for Chrome driver
		System.setProperty("webdriver.chrome.driver", "/Users/yuhihakozaki/Desktop/workspace/SeleniumDBMS/chromedriver");
		WebDriver driver = new ChromeDriver();
		FluentWait wait = new FluentWait(driver);
		
		//Launch website
		driver.navigate().to("https://www.instagram.com/accounts/login/");
		
		wait.withTimeout(Duration.ofMillis(20000));
		wait.pollingEvery(Duration.ofMillis(250));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotInteractableException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated((By.name("username"))));
		
		//Enter Username
		driver.findElement(By.name("username")).sendKeys(username);
		
		//Enter password
		driver.findElement((By.name("password"))).sendKeys(password);
		driver.findElement(By.name("password")).sendKeys(Keys.RETURN);
		
		//navigate to the account deletion site
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div/div/div/div[2]/div[7]/div/div/a/div")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.navigate().to("https://www.instagram.com/accounts/login/?next=%2Faccounts%2Fremove%2Frequest%2Fpermanent%2F");
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deletion-reason")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.id("deletion-reason")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div/div[2]/section/form/p/select/option[10]")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/section/form/p/select/option[10]")).click();
		
		//Enter password and delete
		driver.findElement((By.name("enc_password"))).sendKeys(password);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm-button")));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//		driver.findElement(By.id("confirm-button")).click();
		
	}
}