package nopcommerce.user;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

@Test
public class User_01_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browersDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}

	public void Register_01() {
		//click button Register
		driver.findElement(By.cssSelector("a.ico-register")).click();
		// enter info register
		driver.findElement(By.cssSelector("input#gender-female")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("");
		Select selectDay = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		selectDay.selectByVisibleText("17");
		Select selectMounth = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		selectMounth.selectByVisibleText("3");
		Select selectYear = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		selectYear.selectByVisibleText("1995");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("");
		driver.findElement(By.cssSelector("button#register-button")).click();
	}
	
	@AfterClass
	public void afterClass() {
		
	}
}
