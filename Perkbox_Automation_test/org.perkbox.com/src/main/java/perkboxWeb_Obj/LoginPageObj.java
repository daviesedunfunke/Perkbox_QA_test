/**
 * 
 */
package perkboxWeb_Obj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author d.edunfunke
 *
 */
public class LoginPageObj {
	
	WebDriver driver;
	String frontXpath = "//*[@id='login__global-login__tenant-select__";
	String rearXpath = "__tenant__title']";
	
	@FindBy(xpath = "//*[@id='login__global-login__field-email']") WebElement yourEmail;
	@FindBy(xpath = "//*[@id='login__global-login__btn-submit']") WebElement continueBut;
	
	@FindBy(xpath = "//*[@id='login__global-login__tenant-select__btn-submit']/span/span") WebElement subBut;
	@FindBy(xpath = "//*[@id='login__global-login__tenant-select__title']/span") WebElement titleMgs;
	
	public LoginPageObj(WebDriver driver){
		
		this.driver = driver;
		
	}
	
	public void userLogEmailObj(String userEmail){
		
		yourEmail.clear();
		yourEmail.sendKeys(userEmail);
		continueBut.click();
		
	}
	
	public void userLogAcctObj(String companyAcct){
		
		driver.findElement(By.xpath(frontXpath + companyAcct + rearXpath)).click();
		subBut.click();
		
	}
}
