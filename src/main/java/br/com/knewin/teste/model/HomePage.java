package br.com.knewin.teste.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	public static final int QUANTITY_OF_LOADED_NEWS = 10;
	private static final String LAST_LOADED_NEWS_XPATH = "/html/body/div[4]/div[4]/div/div/div";
	
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

	public void loadMoreNews() {
		this.driver.manage().window().setPosition(this.getLoadMoreButton().getLocation());
		this.driver.manage().window().fullscreen();
		
		int quantityOfLoadedNews = this.getLastNewsUrls().size() + QUANTITY_OF_LOADED_NEWS;
		By lastLoadedNewsBy = By.xpath(LAST_LOADED_NEWS_XPATH + "[" + quantityOfLoadedNews + "]");
		
		this.getLoadMoreButton().click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
		wait.until(ExpectedConditions.presenceOfElementLocated(lastLoadedNewsBy));
	}
}
