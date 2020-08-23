package br.com.knewin.teste;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Execute {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Java\\workspace_knewin_teste\\teste\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		String homeURL = "https://www.infomoney.com.br/mercados/";
		driver.get(homeURL);
		List<WebElement> lastNewsElements = driver.findElements(By.xpath("//div[@id='infiniteScroll']//div[contains(@class, 'img-container')]/a"));
		List<String> newsURLList = new ArrayList<String>();
		for (WebElement webElement : lastNewsElements) {
			String newsURL = webElement.getAttribute("href");
			newsURLList.add(newsURL);
		}
		
		for (String newsURL : newsURLList) {
			driver.get(newsURL);
			WebElement newsHeader = driver.findElement(By.xpath("/html/body/div[4]/article/div[1]/div/div"));
			System.out.println(newsHeader.findElement(By.className("page-title-1")).getText());
			System.out.println(newsURL);
			System.out.println(newsHeader.findElement(By.className("article-lead")).getText());
			System.out.println(newsHeader.findElement(By.className("author-name")).getText());
		}
		
		try {
			driver.close();
			driver.quit();
		} catch (NullPointerException e) {
			System.out.println("Váriavel está nula neste ponto!!!");
		}
	}

}
