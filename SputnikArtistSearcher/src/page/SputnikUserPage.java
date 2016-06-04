package page;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import data.FavoriteAlbumsPages;
import data.UnwantedArtists;

public class SputnikUserPage extends Page {

	public SputnikUserPage(String page_url) {
		super(page_url);
	}
	
	public FavoriteAlbumsPages getFavoriteAlbumsPages(UnwantedArtists unwanted_artists) {
		
		Elements elements = getPage_body().select(".profilebox ");
		
		FavoriteAlbumsPages albums_pages = new FavoriteAlbumsPages();
		
		// Get only the albums with 4.0, 4.5 and 5.0 rating points
		for (int i = 0; i < 3; i++) {
			Elements albums = elements.get(i).parent().select(".default > td > a, .default2 > td > a");
			
			for (Element element : albums) {
				
				String artist_name = element.select("font").first().ownText().toLowerCase();
				
				if (!unwanted_artists.contains(artist_name)) {
					System.out.println(artist_name);
					albums_pages.add(new SputnikAlbumPage(getPage_url().getProtocol() + "://" + getPage_url().getHost() + element.attr("href")));
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.getStackTrace();
					}
					
				}
			}
		}
		return albums_pages;
	}
	
}
