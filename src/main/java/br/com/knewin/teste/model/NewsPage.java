package br.com.knewin.teste.model;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewsPage {

	private WebDriver driver;

	private By newsHeaderBy = By.xpath("/html/body/div[4]/article/div[1]/div/div");
	private By newsTitleBy = By.className("page-title-1");
	private By newsSubTitleBy = By.className("article-lead");
	private By newsAuthorBy = By.className("author-name");
	private By newsDateBy = By.tagName("time");
	private By newsContentBy = By.className("article-content");

	public NewsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void getNews(List<String> newsURLList) {
		for (String newsURL : newsURLList) {
			driver.get(newsURL);
			WebElement newsHeader = driver.findElement(newsHeaderBy);
			System.out.println(newsURL);
			System.out.println(newsHeader.findElement(newsTitleBy).getText());
			System.out.println(newsHeader.findElement(newsSubTitleBy).getText());
			System.out.println(newsHeader.findElement(newsAuthorBy).getText());
			System.out.println(newsHeader.findElement(newsDateBy).getText());

			System.out.println(driver.findElement(newsContentBy).getText().replaceAll("PUBLICIDADE", ""));
			break;
		}
	}

}
