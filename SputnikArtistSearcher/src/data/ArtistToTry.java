package data;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class ArtistToTry implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String artist_name;
	private String best_album_name;
	private String main_tag;
	private URL sputnik_url;
	private URL youtube_url;
	
	public ArtistToTry(String artist_name, String best_album_name, String main_tag, URL sputnik_url) {
		this.artist_name = artist_name.toLowerCase();
		this.best_album_name = best_album_name.toLowerCase();
		this.main_tag = main_tag.toLowerCase();
		this.sputnik_url = sputnik_url;
		
		setYoutube_url();
	}
	
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getBest_album_name() {
		return best_album_name;
	}
	public void setBest_album_name(String best_album_name) {
		this.best_album_name = best_album_name;
	}
	public String getMain_tag() {
		return main_tag;
	}
	public void setMain_tag(String main_tag) {
		this.main_tag = main_tag;
	}
	public URL getSputnik_url() {
		return sputnik_url;
	}
	public void setSputnik_url(URL sputnik_url) {
		this.sputnik_url = sputnik_url;
	}
	public URL getYoutube_url() {
		return youtube_url;
	}
	public void setYoutube_url() {
		try {
			youtube_url = new URL("https://www.youtube.com/results?search_query=" + (artist_name + "+"  + best_album_name).replace(" ", "+"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
