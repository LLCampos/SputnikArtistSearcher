package page;

import org.jsoup.nodes.Element;

public class SputnikAlbumPage extends Page {

	public SputnikAlbumPage(String page_url) {
		super(page_url);
	}

	public SputnikArtistPage getArtistPage() {
		
		Element element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > div > div:eq(0) > div:eq(0) > h1 > a").first();
		
		if (element == null) {
			element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > table > tbody > tr > td > table:eq(0) > tbody > tr > td:eq(1) > a").first();
		}
	    
		String path = element.attr("href");
		return new SputnikArtistPage(getPage_url().getProtocol() + "://" + getPage_url().getHost() + "/" + path);
	}
	
	public String getAlbumName() {
		Element element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > div > div:eq(0) > div:eq(0) > h1 > span").first();
		
		if (element == null) {
			element = getPage_body().select("table > tbody > tr:eq(1) > td > table:eq(0) > tbody > tr > td > table > tbody > tr:eq(3) > td > table > tbody > tr > td > table:eq(0) > tbody > tr > td:eq(1) > font").first();
		}
		
		return element.html();
	}
}
