package salesforce;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Content extends Base_class {
	@BeforeTest
	public void setData(){
		excelName="SalesForce";
		sheetName="TC001";		
	}


	@Test(dataProvider="fetchData")
	public void askQuestion(String question,String details) throws InterruptedException{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//click on App launcher
		driver.findElement(By.xpath("//span[text()='App Launcher']/parent::div")).click();
		//click on view all
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		//search content in search apps and click it
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps')]")).sendKeys("Content");
		driver.findElement(By.xpath("//mark[text()='Content']")).click();
		Thread.sleep(2000);
		//click on chatter
		WebElement chatter_ele = driver.findElement(By.xpath("//span[text()='Chatter']//parent::a"));
		executor.executeScript("arguments[0].click();", chatter_ele);
		//verify the title
		Thread.sleep(2000);
		String title = driver.getTitle();
		if(title.contains("Chatter Home")) {
			System.out.println("Title is Verified");
		}else {
			System.out.println("Wrong Title");
		}
		
		// enter question and details
		driver.findElement(By.xpath("//a[@title='Question']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,'questiontitle')]//textarea")).sendKeys(question);
		driver.findElement(By.xpath("//div[contains(@data-placeholder,'add details')]/p")).sendKeys(details);
		driver.findElement(By.xpath("//button[contains(@title,'Click')]")).click();
		
		//verify entered questions
		Thread.sleep(2000);
		String que = driver.findElement(By.xpath("//div[contains(@class,'questionTitle')]/span")).getText();		
		String det = driver.findElement(By.xpath("//div[contains(@class,'questionBody')]//span")).getText();
		if(que.equals(question) && det.equals(details)) {
			System.out.println("Datas are entered successfully");
		}else {
			System.out.println("Datas are not entered successfully");
		}

	}
}
