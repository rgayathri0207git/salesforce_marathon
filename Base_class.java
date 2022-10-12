package salesforce;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_class {
	
	WebDriver driver;
	String excelName, sheetName;

	@BeforeClass()
	@Parameters({"url","username","password", "browser"})
	public void login(String url, String username,String password, String Browser) throws InterruptedException {
		
		if(Browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions op=new ChromeOptions();
			op.addArguments("--diable-notifications");
			driver=new ChromeDriver(op);
			driver.manage().window().maximize();
		}else if(Browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();;
			EdgeOptions op=new EdgeOptions();
			op.addArguments("--diable-notifications");
			driver=new EdgeDriver(op);
			driver.manage().window().maximize();
		}

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();
		Thread.sleep(3000);

	}

	@AfterTest
	public void close(){
		driver.close();
	}

	@DataProvider(name="fetchData")	
	public String[][] fetchExcelData() throws IOException{
		return ReadExcel.readExcel(excelName, sheetName);
	}
}
