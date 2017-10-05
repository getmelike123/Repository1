package pack1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebSite {
	WebDriver driver;
	
	@BeforeTest
	public void setup(){
		//System.setProperty("webdriver.ie.driver", "G:\\Software\\Selenium\\IEDriverServer.exe");
		//driver=new InternetExplorerDriver();
		//System.setProperty("webdriver.chrome.driver", "G:\\Software\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		//driver=new ChromeDriver(); 
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		driver.get("file:///C:/WebSite/SuperTech.html");
	}
	
	@AfterTest
	public void teardown(){
		System.out.println("Build 1 is success");
		driver.close();
	}
	
	@Test(priority=0)
	public void VerifyTitleAndScrollText(){
		
		String ActualTitle=driver.getTitle();
		String ExpectedTitle="Super Tech IT Solution";
		Assert.assertEquals(ActualTitle, ExpectedTitle);
		
		System.out.println("Main Window Title is : "+ActualTitle);
		String actualStext=driver.findElement(By.xpath(".//*[@id='linkForm']/header/marquee/font")).getText();
		Assert.assertEquals(actualStext, "Welcome to our IT Training Center. We are giving Traning on real project of Software Testing. Selenium with Java , TestNg, JUnit for functional Testing ** Cucumber for BDD Testing ** SoapUI for Webservice Testing ** MS SQL Server, MySql, Oracle Database for Backend Testing ** Appium for Mobile Testing ** Jmeter for Performance Testing**");
		System.out.println("Scroll Text is : "+driver.findElement(By.xpath(".//*[@id='linkForm']/header/marquee/font")).getText());
		
	}
	
	@Test(priority=1)
	public void ShowConfirmBoxTextVerify() throws InterruptedException{
		driver.findElement(By.xpath(".//*[@id='btnConfirm']")).click();
		Thread.sleep(5000);
		Alert alt1=driver.switchTo().alert();
		String actualText=alt1.getText();
		String expectedText="Chose an option.";
		Assert.assertEquals(actualText, expectedText);
		alt1.accept();
		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='output']")).getText(), "Confirmed.");
	}
	@Test(priority=2)
	public void ShowAlertBoxTextVerify() throws InterruptedException{
		driver.findElement(By.xpath(".//*[@id='btnAlert']")).click();
		Thread.sleep(5000);
		Alert alt2=driver.switchTo().alert();
		String actualText=alt2.getText();
		String expectedText="I'm blocking!";
		Assert.assertEquals(actualText, expectedText);
		alt2.accept();
		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='output']")).getText(), "Alert is gone.");
		
	}
	@Test(priority=3)
	public void ShowPromotBoxTextVerify() throws InterruptedException{
		driver.findElement(By.xpath(".//*[@id='btnPrompt']")).click();
		Thread.sleep(5000);
		Alert alt3=driver.switchTo().alert();
		String actualText=alt3.getText();
		String expectedText="What's the best web QA tool?";
		Assert.assertEquals(actualText, expectedText);
		alt3.accept();
		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='output']")).getText(), "Selenium");
	}
	

}
