package page;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class Page {
	
	private URL page_url;
	private Element page_body;
	
	public Page(String page_url) {
		
		try {
			this.page_url = new URL(page_url);
		} catch (MalformedURLException e) {
			e.getStackTrace();
		}
		
		try {
			this.page_body = Jsoup.connect(this.page_url.toString()).get().body();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Element getPage_body() {
		return page_body;
	}
	
	public URL getPage_url() {
		return page_url;
	}
	
}
