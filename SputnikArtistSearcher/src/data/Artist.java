package data;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This Class represents an Music Artist or Group.
 *
 * @author Luis Campos
 */

//TODO Change class name to Artist or MusicGroup
public class Artist implements Serializable {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The artist or musical group name.
	 */
	private String artist_name;
	
	/**
	 * The name of one of the albums of the artist. 
	 */
	private String album_name;
	
	/**
	 * The main genre of the artist.
	 */
	private String main_tag;
	
	/**
	 * The SputnikMusic artist/group URL.
	 */
	private URL sputnik_url;
	
	/**
	 * The URL of a search of Youtube, using the name of the artist and the name of the album.
	 */
	private URL youtube_url;
	
	/**
	 * 
	 * Creates a new artist/music group.
	 * 
	 * @param artist_name Name of artist/music group.
	 * @param best_album_name Name of album.
	 * @param main_tag Name of genre.
	 * @param sputnik_url URL for the artist/music group SputnikMusic page.
	 */
	public Artist(String artist_name, String best_album_name, String main_tag, URL sputnik_url) {
		this.artist_name = artist_name.toLowerCase();
		this.album_name = best_album_name.toLowerCase();
		this.main_tag = main_tag.toLowerCase();
		this.sputnik_url = sputnik_url;
		
		setYoutube_url();
	}
	
	
	/**
	 * Gets the artist or musical group name.
	 *
	 * @return the artist or musical group name
	 */
	public String getArtist_name() {
		return artist_name;
	}
	
	/**
	 * Sets the artist or musical group name.
	 *
	 * @param artist_name the new artist or musical group name
	 */
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	
	/**
	 * Gets the name of one of the albums of the artist.
	 *
	 * @return the name of one of the albums of the artist
	 */
	public String getAlbum_name() {
		return album_name;
	}
	
	/**
	 * Sets the name of one of the albums of the artist.
	 *
	 * @param album_name the new name of one of the albums of the artist
	 */
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	
	/**
	 * Gets the main genre of the artist.
	 *
	 * @return the main genre of the artist
	 */
	public String getMain_tag() {
		return main_tag;
	}
	
	/**
	 * Sets the main genre of the artist.
	 *
	 * @param main_tag the new main genre of the artist
	 */
	public void setMain_tag(String main_tag) {
		this.main_tag = main_tag;
	}
	
	/**
	 * Gets the SputnikMusic artist/group URL.
	 *
	 * @return the SputnikMusic artist/group URL
	 */
	public URL getSputnik_url() {
		return sputnik_url;
	}
	
	/**
	 * Sets the SputnikMusic artist/group URL.
	 *
	 * @param sputnik_url the new SputnikMusic artist/group URL
	 */
	public void setSputnik_url(URL sputnik_url) {
		this.sputnik_url = sputnik_url;
	}
	
	/**
	 * Gets the URL of a search of Youtube, using the name of the artist and the name of the album.
	 *
	 * @return the URL of a search of Youtube, using the name of the artist and the name of the album
	 */
	public URL getYoutube_url() {
		return youtube_url;
	}
	
	/**
	 * Sets the youtube_url instance variable. 
	 * Creates an URL which corresponds to a youtube search for the artist name + album name.
	 */
	public void setYoutube_url() {
		try {
			youtube_url = new URL("https://www.youtube.com/results?search_query=" + (artist_name + "+"  + album_name).replace(" ", "+"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
