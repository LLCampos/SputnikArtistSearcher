package page;

import java.net.MalformedURLException;

import org.jsoup.nodes.Element;

/**
 * This class is used to represent a SputnikMusic album page.
 * 
 * @author Luis Campos
 *
 */

public class SputnikAlbumPage extends SputnikPage {
	
	/**
	 * 
	 * @param album_page_url Is the URL of the SputnikMusic album page.
	 * @throws MalformedURLException
	 */
	public SputnikAlbumPage(String album_page_url) throws MalformedURLException, IllegalArgumentException {
		super(album_page_url);
		
		// There are 3 types of URLs that corresponde to album pages. If the URL given does not match any of them, 
		// throw exception.
		String path_first = this.getPage_url().getPath().split("/")[1];
		if ((!path_first.equals("review")) && (!path_first.equals("album")) && (!path_first.equals("soundoff.php"))) {
			throw new IllegalArgumentException("The URL has to be of a album page.");
		}
		
	}

	public SputnikArtistPage getArtistPage() {
		
		Element element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > div > div:eq(0) > div:eq(0) > h1 > a").first();
		
		if (element == null) {
			element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > table > tbody > tr > td > table:eq(0) > tbody > tr > td:eq(1) > a").first();
		}
		
		String path = element.attr("href");
		
		SputnikArtistPage artist_page = null;
		
		try {
			artist_page = new SputnikArtistPage(getPage_url().getProtocol() + "://" + getPage_url().getHost() + "/" + path);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			System.out.println("There was some problem in the formation of the URL.");
		}
		
		return artist_page;
	}
	
	public String getAlbumName() {
		Element element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > div > div:eq(0) > div:eq(0) > h1 > span").first();
		
		if (element == null) {
			element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > table > tbody > tr > td > table:eq(0) > tbody > tr > td:eq(1) > font").first();
		}
		
		return element.html();
	}
}
