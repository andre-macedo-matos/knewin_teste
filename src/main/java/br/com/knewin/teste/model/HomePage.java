package br.com.knewin.teste.model;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private By lastNewsBy = By.xpath("//div[@id='infiniteScroll']//div[contains(@class, 'img-container')]/a");
	private By loadMoreButtonBy = By.xpath("//div[@id='infinite-handle']");
	
	private List<String> lastNewsUrls;
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getLoadMoreButton() {
		return this.driver.findElement(this.loadMoreButtonBy);
	}

	public List<String> getLastNewsUrls() {
		List<WebElement> lastNewsElements = this.driver.findElements(this.lastNewsBy);
		this.lastNewsUrls = new ArrayList<String>();
		for (WebElement webElement : lastNewsElements) {
			String newsURL = webElement.getAttribute("href");
			this.lastNewsUrls.add(newsURL);
		}
		
		return this.lastNewsUrls;
	}

	public void setLastNewsUrls(List<String> lastNewsUrls) {
		this.lastNewsUrls = lastNewsUrls;
	}

	public void setLoadMoreButtonBy(By loadMoreButtonBy) {
		this.loadMoreButtonBy = loadMoreButtonBy;
	}
}
