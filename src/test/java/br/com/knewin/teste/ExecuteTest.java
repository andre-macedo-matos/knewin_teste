package br.com.knewin.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExecuteTest {

	private static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass(){
		System.setProperty("webdriver.chrome.driver", "D:\\Java\\workspace_knewin_teste\\teste\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://www.infomoney.com.br/mercados/");
	}

	@Test
	public void givenURL_shouldGetPageTitle( ) {
		String actualTitle = driver.getTitle();
		String expectedTitle = "MERCADOS | InfoMoney";
		assertEquals("Erro ao testar os Títulos", expectedTitle, actualTitle);
	}
	
	@Test
	public void givenLastNewsXpath_shouldContainsSectionHeader( ) {
		WebElement lastNewsElement = driver.findElement(By.xpath("/html/body/div[4]/div[4]/div"));
		assertTrue("Não foi encontrado o Header!!!", lastNewsElement.getText().contains("Últimas de Mercado"));
	}
	
	@Test
	public void givenLastNewsXpath_shouldContainsLoadMoreNewsButton( ) {
		WebElement lastNewsElement = driver.findElement(By.xpath("/html/body/div[4]/div[4]/div"));
		assertTrue("Não foi encontrado o Header!!!", lastNewsElement.getText().contains("Carregar mais"));
	}
	
	

	@AfterClass
	public static void tearDownAfterClass() {
		try {
			driver.close();
			driver.quit();
		} catch (NullPointerException e) {
			System.out.println("Váriavel está nula neste ponto!!!");
		}
	}
}
