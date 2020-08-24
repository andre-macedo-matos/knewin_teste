package br.com.knewin.teste.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewsPage {

	public static final String NEWS_CONTENT_PUBLICITY_TERM = "PUBLICIDADE";
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

	public void printNews(List<String> newsURLList) {
		for (String newsURL : newsURLList) {
			System.out.println(getNews(newsURL));
		}
	}

	public void printNews(List<String> newsURLList, int quantity) {
		for (int i = 0; i < quantity; i++) {
			String url = newsURLList.get(i);
			System.out.println(getNews(url));
		}
	}

	public String getNews(String newsURL) {
		this.driver.get(newsURL);
		
		WebElement newsHeader = driver.findElement(newsHeaderBy);
		WebElement newsDate = newsHeader.findElement(newsDateBy);
		WebElement newsContent = driver.findElement(newsContentBy);
		String news = newsURL + "\n" +
				newsHeader.findElement(newsTitleBy).getText() + "\n" + 
				newsHeader.findElement(newsSubTitleBy).getText() + "\n"+ 
				newsHeader.findElement(newsAuthorBy).getText() + "\n" + 
				getFormatedNewsDateTime(newsDate) + "\n" + 
				getFormatedNewsContent(newsContent) + "\n";

		return news;
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
