package page;

import java.awt.List;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SputnikUserPage extends Page {

	public SputnikUserPage(String page_url) {
		super(page_url);
	}
	
	public ArrayList<SputnikAlbumPage> getFavoriteAlbumsPages() {
		
		Elements elements = getPage_body().select(".profilebox ");
		
		ArrayList<SputnikAlbumPage> albums_pages = new ArrayList<SputnikAlbumPage>();
		
		// Get only the albums with 4.0, 4.5 and 5.0 rating points
		for (int i = 0; i < 3; i++) {
			Elements albums = elements.get(i).parent().select(".default > td > a, .default2 > td > a");
			for (Element element : albums) {
				albums_pages.add(new SputnikAlbumPage(getPage_url().getProtocol() + "://" + getPage_url().getHost() + element.attr("href")));
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		}
		return albums_pages;
	}
	
}
