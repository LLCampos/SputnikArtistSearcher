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
	 * Instantiates a new object of this class, representing the page given in the argument.
	 *
	 * @param album_page_url Is the URL of the SputnikMusic album page.
	 * @throws MalformedURLException 
	 * @throws IllegalArgumentException 
	 */
	public SputnikAlbumPage(String album_page_url) throws MalformedURLException, IllegalArgumentException {
		super(album_page_url);
		
		// There are 3 types of URLs that corresponds to album pages. If the URL given does not match any of them, 
		// throw exception.
		String path_first = this.getPage_url().getPath().split("/")[1];
		if ((!path_first.equals("review")) && (!path_first.equals("album")) && (!path_first.equals("soundoff.php"))) {
			throw new IllegalArgumentException("The URL has to be of a album page.");
		}
		
	}
	
	/**
	 * Gets the page of the artists/music group that play in this album.
	 *
	 * @return the artist page
	 */
	public SputnikArtistPage getArtistPage() {
		
		// Tries to get a Element which will contain the artist name.
		Element element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > div > div:eq(0) > div:eq(0) > h1 > a").first();
		
		// If it doesn't work, try other way.
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
	
	/**
	 * Gets the album name.
	 *
	 * @return the album name
	 */
	public String getAlbumName() {
		
		// Tries to get a Element which will contain the album name.
		Element element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > div > div:eq(0) > div:eq(0) > h1 > span").first();
		
		// If it doesn't work, try other way.
		if (element == null) {
			element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > table > tbody > tr > td > table:eq(0) > tbody > tr > td:eq(1) > font").first();
		}
		
		return element.html();
	}
}
