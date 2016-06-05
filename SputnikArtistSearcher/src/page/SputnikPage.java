package page;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 * This class is used to represent a SputnikMusic web page.
 * 
 * @author Luis Campos
 */

public class SputnikPage {
	
	/**
	 * URL of the page the object represents.
	 */
	private URL page_url;
	
	/**
	 * An Element object representing everything inside the <body> tag.
	 */
	private Element page_body;
	
	
	/**
	 * @param page_url a {@code String} representing the URL of the page.
	 * @throws MalformedURLException if the URL given is malformed.
	 */
	public SputnikPage(String page_url) throws MalformedURLException, IllegalArgumentException {
		
		this.page_url = new URL(page_url);
		
		if (!this.page_url.getHost().equals("www.sputnikmusic.com")) {
			throw new IllegalArgumentException("The URL given should redirect to a SputnikMusic hosted webage");
		}; 
		
		try {
			this.page_body = Jsoup.connect(this.page_url.toString()).get().body();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return An {@code Element} representing the HTML body of the page.
	 */
	public Element getPage_body() {
		return page_body;
	}
	
	/**
	 *
	 * @return The page URL.
	 */
	public URL getPage_url() {
		return page_url;
	}
	
}
