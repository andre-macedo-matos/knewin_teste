package br.com.knewin.teste.model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.com.knewin.teste.Execute;

public class HomePageTest {

	private static WebDriver driver;
	private static HomePage homePage;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = Execute.getDriver();
		driver.get(Execute.URL);
		homePage = new HomePage(driver);
	}


	@Test
	public void givenUrl_shouldGiveHomePageTitle() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "MERCADOS | InfoMoney";
		assertEquals("Erro ao testar os Títulos", expectedTitle, actualTitle);
	}
	
	@Test
	public void givenHomePage_shouldGetLoadMoreButton() {
		String actualButtonText = homePage.getLoadMoreButton().getText();
		String expectedButtonText = "Carregar mais";
		assertEquals("Erro ao testar texto do Botão", expectedButtonText, actualButtonText);
	}
	
	@Test
	public void givenHomePage_shouldGetTenFirstNewsURL() {
		int actualQuantityOfNews = homePage.getLastNewsUrls().size();
		int expectedQuantityOfNews = HomePage.INITIAL_QUANTITY_OF_LOADED_NEWS;
		assertEquals("Erro ao testar quantidade de noticias!!!", expectedQuantityOfNews, actualQuantityOfNews);;
	}
	
	@Test
	public void whenClickOnLoadMore_shouldGetTwentyNewsURL() {
		int actualQuantityOfNews = homePage.loadMoreNews().getLastNewsUrls().size();
		int expectedQuantityOfNews = 20;
		assertEquals("Erro ao testar quantidade de noticias!!!", expectedQuantityOfNews, actualQuantityOfNews);;
	}
	
	@AfterClass 
	public static void tearDownAfterClass() throws Exception {
		driver.close();
		driver.quit();
	}

}
