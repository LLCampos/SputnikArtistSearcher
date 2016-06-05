package page;

import java.net.MalformedURLException;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import data.FavoriteAlbumsPages;
import data.StringHashSetFromFile;

public class SputnikUserPage extends Page {

	public SputnikUserPage(String page_url) throws MalformedURLException {
		//TODO Checkar se o URL é da página de um user
		super(page_url);
	}
	
	public FavoriteAlbumsPages getFavoriteAlbumsPages(StringHashSetFromFile unwanted_artists) {
		
		Elements elements = getPage_body().select(".profilebox ");
		
		FavoriteAlbumsPages albums_pages = new FavoriteAlbumsPages();
		
		// Get only the albums with 4.0, 4.5 and 5.0 rating points
		for (int i = 0; i < 3; i++) {
			Elements albums = elements.get(i).parent().select(".default > td > a, .default2 > td > a");
			
			for (Element element : albums) {
				
				String artist_name = element.select("font").first().ownText().toLowerCase();
				
				if (!unwanted_artists.contains(artist_name)) {
					try {
						System.out.println(artist_name);
						SputnikAlbumPage album_page = new SputnikAlbumPage(getPage_url().getProtocol() + "://" + getPage_url().getHost() + element.attr("href"));
						albums_pages.add(album_page);
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
