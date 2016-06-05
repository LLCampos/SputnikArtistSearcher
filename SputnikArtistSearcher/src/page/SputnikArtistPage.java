package page;

import java.net.MalformedURLException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SputnikArtistPage extends Page {
	
	public SputnikArtistPage(String page_url) throws MalformedURLException {
		//TODO Checkar se o URL é da página de um artista
		super(page_url);
	}
	
	public String getArtistName() {
		Elements element = getPage_body().select("table > tbody > tr:eq(1) > td > table > tbody > tr > td > table:eq(0) > tbody > tr:eq(3) > td > table > tbody > tr > td:eq(1) > table:eq(0) > tbody > tr > td > font > b");
		return element.html().toLowerCase();
	}
	
	public String getArtistTag() {
		Element element = getPage_body().select(".tags > .tag > a").first();
		return element.html().toLowerCase();	
	}
}
