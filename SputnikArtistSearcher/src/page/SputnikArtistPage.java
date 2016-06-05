package page;

import java.net.MalformedURLException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SputnikArtistPage extends SputnikPage {
	
	public SputnikArtistPage(String page_url) throws MalformedURLException, IllegalArgumentException {
		super(page_url);
		
		if (!getPath_first().equals("bands")) {
			System.out.println(getPage_url().toString());
			throw new IllegalArgumentException("The URL has to be of a artist page.");
		}
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
