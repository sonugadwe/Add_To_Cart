package com;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String[] itemsNeeded= {"Cucumber","Brocolli","Beetroot"};
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		//implicit wait
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
		addItems(driver,itemsNeeded);
	}
	public static  void addItems(WebDriver driver,String[] itemsNeeded)

	{
		WebDriverWait w=new WebDriverWait(driver, 5);
		
		int j=0;
		List<WebElement> products=driver.findElements(By.cssSelector("h4.product-name"));
		for(int i=0; i<products.size(); i++)
		{
			//String name=products.get(i).getText();
			//Brocolli - 1 kg
			//split in two index Brocolli 0 th index, 1 kg 1th index 
			//format it to get actual vegetable name
			String[] name=products.get(i).getText().split("-");
			String formattedName=name[0].trim();
			//check whether names you extracted is present in array or not
			
			//convert array into arraylist easy search
			List itemsNeededlist=Arrays.asList(itemsNeeded);
			
			/*hardcoded
			 * if(name.contains("Cucumber"))
			{
				//add to cart element
				driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();
				break;
			}*/
			
			 if(itemsNeededlist.contains(formattedName))
				{
				 j++;
					//add to cart element
					driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
					if(j==itemsNeeded.length) {
						break;
					}
				}
			driver.findElement(By.cssSelector("img[alt='Cart']")).click();
			driver.findElement(By.xpath("//button[contains (text(),'PROCEED TO CHECKOUT')]")).click();
			
			w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
			driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
			driver.findElement(By.cssSelector("Button.promoBtn")).click();
			
			System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
			w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
			driver.findElement(By.cssSelector("#root > div > div > div > div > button")).click(); 
			//#root > div > div > div > div > br:nth-child(13)
			Select s=new Select(driver.findElement(By.cssSelector("#root > div > div > div > div > div > select")));
			s.selectByVisibleText("India");
			//*[@class='chkAgree']
			
			
		}
	}

}



