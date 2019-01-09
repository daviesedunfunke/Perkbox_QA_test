/**
 * 
 */
package perkboxTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import perkboxWeb_Obj.LoginPageObj;

/**
 * @author d.edunfunke
 *
 */
public class LoginWithAccount {
	
	WebDriver driver;
	String browserName = "chrome";
	WebDriverWait wait;
	
	@BeforeMethod
	public void browserActive() throws Throwable {
		
		File conFile = new File (".\\conFile\\config.property");
		FileInputStream conStream = new FileInputStream(conFile);
		Properties property = new Properties();
		property.load(conStream);
		String url = property.getProperty("appUrl");
				
		if (browserName.equalsIgnoreCase("ie")){
			
			String iePath = property.getProperty("ieDriver");
			System.setProperty("webdriver.ie.driver", iePath);
			driver = new InternetExplorerDriver();
		
		}else if (browserName.equalsIgnoreCase("chrome")){
			
			String chromePath = property.getProperty("chromeDriver");
			System.setProperty("webdriver.chrome.driver", chromePath);
			driver = new ChromeDriver();
			
		}else if (browserName.equalsIgnoreCase("firefox")){
			
			String foxPath = property.getProperty("foxDriver");
			System.setProperty("webdriver.gecko.driver", foxPath);
			driver = new FirefoxDriver();
			
		}else if (browserName.equalsIgnoreCase("htmlDriver")){
			
			driver = new HtmlUnitDriver();
			
		}else{
			
			System.out.println("Using Unsupported Browser");
			System.out.println("Application Under Test Terminated");
			
		}
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(driver.getTitle(), "Perkbox | Login");
		
	}

	@AfterMethod
	public void tearDownBrowser(){
		
		driver.close();
		
	}

	@Test(priority = 1, description = "Verify the user can enter valid email address & click on the CONTINUE button")
	public void userSignUpTest() throws Throwable{
		
		LoginPageObj loginTest = PageFactory.initElements(driver, LoginPageObj.class);
		File conFile = new File ("./conFile/config.property");
		FileInputStream conStream = new FileInputStream(conFile);
		Properties property = new Properties();
		property.load(conStream);
		String newUserName = property.getProperty("userEmailId");
		loginTest.userLogEmailObj(newUserName);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='login__global-login__tenant-select__title']/span")).getText(), "Please select a company you want to sign in to");
		
	}
		
	@Test(priority = 2, description = "Verify the user can selects company account for sign In")
	public void userSelAcct() throws Throwable {
		
		LoginPageObj loginTest = PageFactory.initElements(driver, LoginPageObj.class);
		File conFile = new File ("./conFile/config.property");
		FileInputStream conStream = new FileInputStream(conFile);
		Properties property = new Properties();
		property.load(conStream);
		String newUserName = property.getProperty("userEmailId");
		String acctName = property.getProperty("acctName1");
		loginTest.userLogEmailObj(newUserName);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='login__global-login__tenant-select__title']/span")).getText(), "Please select a company you want to sign in to");
		loginTest.userLogAcctObj(acctName);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='login__default-login__card-title']/span[1]")).getText(), "Hey there");
		
	}
	
}
