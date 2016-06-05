package page;

import java.net.MalformedURLException;
import java.util.HashSet;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import data.StringHashSetFromFile;

/**
 * 
 * This class is used to represent a SputnikMusic user's page.
 * 
 * @author Luis Campos
 *
 */
public class SputnikUserPage extends SputnikPage {

	/**
	 * Instantiates a new object of this class, representing the page given in the argument.
	 *
	 * @param album_page_url Is the URL of the SputnikMusic user's page.
	 * @throws MalformedURLException 
	 * @throws IllegalArgumentException If the URL given was not the URL of a user's page.
	 */
	public SputnikUserPage(String page_url) throws MalformedURLException, IllegalArgumentException {
		super(page_url);
		
		if (!getPath_first().equals("uservote.php")) {
			throw new IllegalArgumentException("The URL has to be of a user ratings page.");
		}
	}
	
	/**
	 * Gets the pages of the favorite albums of the user. By favorites, it is consideres the albums that 
	 * were given the 3 highest ratings.
	 *
	 * @param unwanted_artists A set of artist whose albums the algorithm will ignore.
	 * @return the favorite albums pages
	 */
	public HashSet<SputnikAlbumPage> getFavoriteAlbumsPages(StringHashSetFromFile unwanted_artists) {
		
		Elements elements = getPage_body().select(".profilebox ");
		
		HashSet<SputnikAlbumPage> albums_pages = new HashSet<SputnikAlbumPage>();
		
		// Get only the albums with the 3 highest rating points
		for (int i = 0; i < 3; i++) {
			Elements albums = elements.get(i).parent().select(".default > td > a, .default2 > td > a");
			
			for (Element element : albums) {
				
				String artist_name = element.select("font").first().ownText().toLowerCase();
				
				if (!unwanted_artists.contains(artist_name)) {
					try {
						System.out.println(artist_name);
						SputnikAlbumPage album_page = new SputnikAlbumPage(getPage_url().getProtocol() + "://" + getPage_url().getHost() + element.attr("href"));
						albums_pages.add(album_page);
						// Wait a little to not overuse SputnikMusic servers.
						Thread.sleep(1000);
						
					} catch (MalformedURLException e){
						System.out.println(e.getMessage());
						System.out.println("There was some problem in the formation of the URL.");
						continue;
					} catch (InterruptedException e) {
						e.getStackTrace();
					}
					
				}
			}
		}
		return albums_pages;
	}
	
}
