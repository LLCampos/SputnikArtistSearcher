package page;

import java.net.MalformedURLException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * This class represents a SputnikMusic artist's page.
 * 
 * @author Luis Campos
 *
 */
public class SputnikArtistPage extends SputnikPage {
	
	/**
	 * Instantiates a new object of this class, representing the page given in the argument.
	 *
	 * @param page_url Is the URL of the SputnikMusic artist page.
	 * @throws MalformedURLException 
	 * @throws IllegalArgumentException If the URL given was not the URL of a artist page.
	 */
	public SputnikArtistPage(String page_url) throws MalformedURLException, IllegalArgumentException {
		super(page_url);
		
		if (!getPath_first().equals("bands")) {
			throw new IllegalArgumentException("The URL has to be of a artist page.");
		}
	}
	
	/**
	 * Gets the artist name.
	 *
	 * @return the artist name
	 */
	public String getArtistName() {
		Elements element = getPage_body().select("table > tbody > tr:eq(1) > td > table > tbody > tr > td > table:eq(0) > tbody > tr:eq(3) > td > table > tbody > tr > td:eq(1) > table:eq(0) > tbody > tr > td > font > b");
		return element.html().toLowerCase();
	}
	
	/**
	 * Gets the artist main tag.
	 *
	 * @return the artist tag
	 */
	public String getArtistTag() {
		Element element = getPage_body().select(".tags > .tag > a").first();
		return element.html().toLowerCase();	
	}
}
