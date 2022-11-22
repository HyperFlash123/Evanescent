import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class Google {

	public static void deleteAccount(String username, String password) {
		
		//declaring the specific executable for Chrome driver
		System.setProperty("webdriver.chrome.driver", "/Users/yuhihakozaki/Desktop/workspace/SeleniumDBMS/chromedriver");
		WebDriver driver = new ChromeDriver();
		FluentWait wait = new FluentWait(driver);
		
		//Launch website
		driver.navigate().to("https://accounts.google.com/ServiceLogin?hl=en&passive=true&continue=https://www.google.com/&ec=GAZAmgQ");
		
		//Enter Gmail
		driver.findElement(By.name("identifier")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span")).click(); 
		
		//wait for the password screen to show up
		wait.withTimeout(Duration.ofMillis(20000));
		wait.pollingEvery(Duration.ofMillis(250));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotInteractableException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//Enter password
		driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/span")).click();
		
		//navigate to the account deletion site
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"gb\"]/div/div[2]/div[2]/div/a"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.navigate().to("https://myaccount.google.com/deleteaccount");
		
		//Enter password
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/span")).click();
		
		//Delete account
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"i4\"]")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("i4")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.findElement(By.id("i5")).click();
		
        //driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/div[2]/c-wiz/div/div[3]/form/div/div[3]/div/div/button")).click();
	}
}