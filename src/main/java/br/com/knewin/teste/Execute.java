package br.com.knewin.teste;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.knewin.teste.model.HomePage;
import br.com.knewin.teste.model.NewsPage;

public class Execute {

	private static final String CHROMEDRIVER = ".\\chromedriver.exe";
	private static final String HEADLESS_ARGUMENT = "--headless";
	private static final String SYSTEM_WEBDRIVER_PROPERTY = "webdriver.chrome.driver";

	public static final String URL = "https://www.infomoney.com.br/mercados/";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		WebDriver driver = getDriver();

		try {
			int quantityOfNews = 0;
			do {
				driver.get(URL);
				System.out.println("Informe quantidade de notícias para coletar, ou \"0\" para encerrar: ");
				quantityOfNews = scanner.nextInt();
				
				HomePage homePage = new HomePage(driver);
				NewsPage newsPage = new NewsPage(driver);
				
				List<String> newsURLList = homePage.getLastNewsUrls();
				
				if( quantityOfNews > newsURLList.size()) {
					// newsURLList = loadMoreNews(quantityOfNews, homePage, newsURLList);
					break;
				}

				newsPage.printNews(newsURLList, quantityOfNews);
			} while (quantityOfNews != 0);

			System.out.println("Encerrando aplicação!\nObrigado por utilizar!!!");
		} catch (NullPointerException e) {
			System.out.println("Váriavel está nula neste ponto!!!");
		} catch (InputMismatchException e) {
			System.out.println("Por favor, informe um valor numérico!!!");
		} finally {
			scanner.close();
			driver.close();
			driver.quit();
		}

	}

	private static List<String> loadMoreNews(int quantityOfNews, List<String> newsURLList) {
		WebDriver driver = getDriver();
		HomePage homePage = new HomePage(driver);
		while (quantityOfNews > newsURLList.size()) {
			homePage.loadMoreNews();
			newsURLList = homePage.getLastNewsUrls();
		}
		return newsURLList;
	}

	public static WebDriver getDriver() {
		System.setProperty(SYSTEM_WEBDRIVER_PROPERTY, CHROMEDRIVER);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments(HEADLESS_ARGUMENT);

		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get(URL);

		return driver;
	}

}
