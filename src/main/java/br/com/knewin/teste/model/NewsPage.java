package br.com.knewin.teste.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewsPage {

	private static final String NEWS_CONTENT_PUBLICITY_TERM = "PUBLICIDADE";
	private static final String NEWS_DATE_FORMATED = "dd/MM/yyyy HH:mm";
	private static final String NEWS_DATE_ATTRIBUTE = "datetime";

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
			
			WebElement newsDate = newsHeader.findElement(newsDateBy);
			System.out.println(getFormatedNewsDateTime(newsDate));
			
			WebElement newsContent = driver.findElement(newsContentBy);
			System.out.println(getFormatedNewsContent(newsContent) + "\n");
			break;
		}
	}

	public String getFormatedNewsContent(WebElement newsContent) {
		String content = newsContent.getText();
		
		int beginIndex = 0;
		int lastIndexOf = content.lastIndexOf("\n");
		content = content.substring(beginIndex, lastIndexOf)
                         .replaceAll(NEWS_CONTENT_PUBLICITY_TERM, "")
				         .replaceAll("\n", "");
		return content;
	}

	public String getFormatedNewsDateTime(WebElement newsDate) {
		LocalDateTime dateTime = parseNewsDate(newsDate);
		String dateTimeFormated = dateTime.format(DateTimeFormatter.ofPattern(NEWS_DATE_FORMATED));
		return dateTimeFormated;
	}

	public LocalDateTime parseNewsDate(WebElement newsDate) {
		String dateTimeAttribute = newsDate.getAttribute(NEWS_DATE_ATTRIBUTE);
		LocalDateTime dateTime = LocalDateTime.parse(dateTimeAttribute, DateTimeFormatter.ISO_DATE_TIME);
		return dateTime;
	}

}
