package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import page.SputnikAlbumPage;
import page.SputnikArtistPage;
import page.SputnikUserPage;

/**
 * 
 * This Class represents a list of musical artists/groups for the user to listen.
 * 
 * @author Luis Campos
 *
 */
public class ArtistsToTry extends ArrayList<ArtistToTry> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Gets a list of ArtistToTry fom a SputnikMusic user page and adds it to this object.
	 *
	 * @param user_page The SputnikMusic user rating page from which to get names of artists.
	 * @param unwanted_artists A set of artists that the user doesn't want to be recommended.
	 * @param unwanted_tags A set of tags, indicating the that the user doesn't want artists of that genres in the recommendations.
	 */
	public void addArtistToTryFromSputnikUserPage(SputnikUserPage user_page, StringHashSetFromFile unwanted_artists, StringHashSetFromFile unwanted_tags) {
		
		FavoriteAlbumsPages favorite_album_pages = user_page.getFavoriteAlbumsPages(unwanted_artists);
		
		for (SputnikAlbumPage album_page : favorite_album_pages) {
			
			SputnikArtistPage album_artist_page = album_page.getArtistPage();
			
			if (album_artist_page == null || unwanted_artists.contains(album_artist_page.getArtistName()) || unwanted_tags.contains(album_artist_page.getArtistTag())) {
				continue;
			}
			
			add(new ArtistToTry(album_artist_page.getArtistName(), album_page.getAlbumName(), album_artist_page.getArtistTag(), album_artist_page.getPage_url()));
		}
	}
	
	/**
	 * Updates this object, removing duplicates and artists that play genres that the user doesn't want to listen to.
	 *
	 * @param A set of artists that the user doesn't want to be recommended.
	 * @param unwanted_tags A set of tags, indicating the that the user doesn't want artists of that genres in the recommendations.
	 */
	public void update(StringHashSetFromFile unwanted_artists, StringHashSetFromFile unwanted_tags) {
		
		 ArtistsToTry list = (ArtistsToTry) this.clone();
		
		for (ArtistToTry artist : list) {
			if (unwanted_artists.contains(artist.getArtist_name()) || unwanted_tags.contains(artist.getMain_tag())) {
				remove(artist);
			}
		}
	}
	
	/**
	 * Loads a instance of this class from a serialised version.
	 *
	 * @param file_path The path to the .ser file.
	 * @return An instance of this Class
	 */
	public static ArtistsToTry loadObject(String file_path) {
		
		ArtistsToTry artists_to_try = null;
		
		try {
			ObjectInputStream is = new ObjectInputStream (new FileInputStream(file_path));
			artists_to_try = (ArtistsToTry) is.readObject();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return artists_to_try;
	}
	
	/**
	 * Serialises this objects and saves it to a file.
	 *
	 * @param file_path The path on which to save the file resulting from the serialisation of this object.
	 */
	public void saveObject(String file_path) {
		try {
			ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream(file_path));
			os.writeObject(this);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
