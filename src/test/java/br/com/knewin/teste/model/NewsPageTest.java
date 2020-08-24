package br.com.knewin.teste.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.com.knewin.teste.Execute;

public class NewsPageTest {

	private static final String NEWS_URL_EXAMPLE = "https://www.infomoney.com.br/mercados/camara-de-sp-aprova-em-1o-turno-alta-do-iss-para-instituicoes-financeiras-impactos-para-b3-e-bancos-sao-limitados/";
	private static WebDriver driver;
	private static NewsPage newsPage;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = Execute.getDriver();
		driver.get(NEWS_URL_EXAMPLE);
		newsPage = new NewsPage(driver);
	}

	@Test
	public void givenUrl_shouldGiveNewsPageTitle_equalsToNewsTitle() {
		String actualTitle = driver.getTitle();
		String actualNews = newsPage.getNews(NEWS_URL_EXAMPLE);
		assertTrue("Erro ao testar Título da aba e da noticia", actualNews.contains(actualTitle));
	}

	@Test
	public void givenUrl_shouldGiveNews_withSubTitle() {
		String actualNews = newsPage.getNews(NEWS_URL_EXAMPLE);
		String expectedSubTitle = "Caso aprovado em definitivo, a alíquota de Impostos sobre Serviços aumentará de "
				+ "2% para 5% desde sua aprovação até o fim de 2020";
		
		assertTrue("Erro ao testar subtitulo na noticia", actualNews.contains(expectedSubTitle));
	}

	@Test
	public void givenUrl_shouldGiveNews_withAuthor() {
		String actualNews = newsPage.getNews(NEWS_URL_EXAMPLE);
		String expectedAuthor = "Por Equipe InfoMoney";
		assertTrue("Erro ao testar autor na noticia", actualNews.contains(expectedAuthor));
	}
	
	@Test
	public void givenUrl_shouldGiveNews_withFormatedDate() {
		String actualNews = newsPage.getNews(NEWS_URL_EXAMPLE);
		String expectedDate = "21/08/2020 11:09";
		assertTrue("Erro ao testar data na noticia", actualNews.contains(expectedDate));
	}
	
	@Test
	public void givenUrl_shouldGiveFormatedNewsContent() {
		String actualNews = newsPage.getNews(NEWS_URL_EXAMPLE);
		String expectedContent = "A Câmara Municipal de São Paulo aprovou em primeiro turno na noite da última quarta-feira (19) o texto do Projeto";
		assertTrue("Erro ao testar conteudo na noticia", actualNews.contains(expectedContent));
		assertFalse("Erro ao testar conteudo na noticia", actualNews.contains(NewsPage.NEWS_CONTENT_PUBLICITY_TERM));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
		driver.quit();
	}

}
