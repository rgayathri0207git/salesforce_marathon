package salesforce;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Individuals extends Base_class {
	
	@BeforeTest
	public void setData(){
		excelName="SalesForce";
		sheetName="TC002";
		
	}
	@Test(dataProvider="fetchData")
	public void createIndividuals(String title, String lastname) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//click on App launcher
		driver.findElement(By.xpath("//span[text()='App Launcher']/parent::div")).click();
		//click on view all
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		//search content in search apps and click it
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps')]")).sendKeys("individuals");
		driver.findElement(By.xpath("//mark[text()='Individuals']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@title='New']/parent::a")).click();
		driver.findElement(By.xpath("//div[contains(@class,'salutation')]//a")).click();
		driver.findElement(By.xpath("//a[@title='"+title+"']")).click();

		driver.findElement(By.xpath("//input[contains(@class,'lastName')]")).sendKeys(lastname);
		driver.findElement(By.xpath("(//span[text()='Save']/parent::button)[2]")).click();

		driver.findElement(By.xpath("//span[text()='App Launcher']/parent::div")).click();
		//click on view all
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		//search Customers in search apps and click it
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps')]")).sendKeys("customers");
		driver.findElement(By.xpath("//mark[text()='Customers']")).click();
		Thread.sleep(2000);
		//click new and verify customer
		driver.findElement(By.xpath("//div[@title='New']")).click();
		driver.findElement(By.xpath("//input[@title='Search Individuals']")).sendKeys(lastname);
		String text = driver.findElement(By.xpath("//mark[@class='data-match']")).getText();
		if(text.equals(lastname)) {
			System.out.println("customer verified");
		}else {
			System.out.println("Customer not verified");
		}

	}

}
