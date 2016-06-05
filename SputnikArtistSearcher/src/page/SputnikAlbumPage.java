package page;

import java.net.MalformedURLException;

import org.jsoup.nodes.Element;

/**
 * This class is used to represent a SputnikMusic album page (<a href="http://www.sputnikmusic.com/review/70879/Radiohead-A-Moon-Shaped-Pool/">Example</a>).
 * 
 * @author Luis Campos
 *
 */

public class SputnikAlbumPage extends SputnikPage {
	
	public int page_type;

	/**
	 * 
	 * @param album_page_url Is the URL of the SputnikMusic album page.
	 * @throws MalformedURLException
	 */
	public SputnikAlbumPage(String album_page_url) throws MalformedURLException {
		// TODO checkar se a página é de album;
		super(album_page_url);
		
		String path_first = this.getPage_url().getPath().split("/")[1];
		
		if (path_first.equals("review")) {
			page_type = 1;
		} else if (path_first.equals("soundoff.php")) {
			page_type = 2;
		}
	}

	public SputnikArtistPage getArtistPage() {
		
		String query_string = null;
		if (page_type == 1) {
			query_string = "table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > div > div:eq(0) > div:eq(0) > h1 > a";
		} else if (page_type == 2) {
			query_string = "table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > table > tbody > tr > td > table:eq(0) > tbody > tr > td:eq(1) > a";
		}
		
		Element element = getPage_body().select(query_string).first();
	    
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
