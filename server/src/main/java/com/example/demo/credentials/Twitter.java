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

public class Twitter {

	public static void deleteAccount(String username, String password) {

		//declaring the specific executable for Chrome driver
		System.setProperty("webdriver.chrome.driver", "/Users/yuhihakozaki/Desktop/workspace/SeleniumDBMS/chromedriver");
		WebDriver driver = new ChromeDriver();
		FluentWait wait = new FluentWait(driver);
		
		//Launch website
		driver.navigate().to("https://twitter.com/i/flow/login");
		
		wait.withTimeout(Duration.ofMillis(20000));
		wait.pollingEvery(Duration.ofMillis(250));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotInteractableException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated((By.name("text"))));
		
		//Enter Username
		driver.findElement(By.name("text")).sendKeys(username);
		driver.findElement(By.name("text")).sendKeys(Keys.RETURN);
		
		//wait for the password screen to show up
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("password"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//Enter password
		driver.findElement((By.name("password"))).sendKeys(password);
		driver.findElement(By.name("password")).sendKeys(Keys.RETURN);
		
		//navigate to the account deletion site
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("/html/body/div[1]/div/div/div[2]/header/div/div/div/div[1]/div[2]/nav/div"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/header/div/div/div/div[1]/div[2]/nav/div")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("/html/body/div[1]/div/div/div[1]/div[2]/div/div/div/div[2]/div/div[3]/div/div/div/div/div[5]/div[3]"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div[2]/div/div/div/div[2]/div/div[3]/div/div/div/div/div[5]/div[3]")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("/html/body/div[1]/div/div/div[1]/div[2]/div/div/div/div[2]/div/div[3]/div/div/div/div/div[5]/section/a[1]"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div[2]/div/div/div/div[2]/div/div[3]/div/div/div/div/div[5]/section/a[1]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/section[1]/div[2]/div[2]/div[1]/a"))));
		driver.navigate().to("https://twitter.com/settings/deactivate");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/section[2]/div[2]/div/div[11]/div")).click();
		
        //Delete account
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// driver.findElement(By.name("current_password")).sendKeys(password);
		// driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/section[2]/div[2]/div[5]/div")).click();

	}
}