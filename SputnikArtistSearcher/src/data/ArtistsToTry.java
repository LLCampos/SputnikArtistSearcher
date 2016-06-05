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
 * The Class ArtistsToTry.
 */
public class ArtistsToTry extends ArrayList<Artist> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Adds the artist to try from sputnik user page.
	 *
	 * @param user_page the user_page
	 * @param unwanted_artists the unwanted_artists
	 * @param unwanted_tags the unwanted_tags
	 */
	public void addArtistToTryFromSputnikUserPage(SputnikUserPage user_page, StringHashSetFromFile unwanted_artists, StringHashSetFromFile unwanted_tags) {
		
		FavoriteAlbumsPages favorite_album_pages = user_page.getFavoriteAlbumsPages(unwanted_artists);
		
		for (SputnikAlbumPage album_page : favorite_album_pages) {
			
			SputnikArtistPage album_artist_page = album_page.getArtistPage();
			
			if (album_artist_page == null || unwanted_artists.contains(album_artist_page.getArtistName()) || unwanted_tags.contains(album_artist_page.getArtistTag())) {
				continue;
			}
			
			add(new Artist(album_artist_page.getArtistName(), album_page.getAlbumName(), album_artist_page.getArtistTag(), album_artist_page.getPage_url()));
		}
	}
	
	/**
	 * Update.
	 *
	 * @param unwanted_artists the unwanted_artists
	 * @param unwanted_tags the unwanted_tags
	 */
	public void update(StringHashSetFromFile unwanted_artists, StringHashSetFromFile unwanted_tags) {
		
		 ArtistsToTry list = (ArtistsToTry) this.clone();
		
		for (Artist artist : list) {
			if (unwanted_artists.contains(artist.getArtist_name()) || unwanted_tags.contains(artist.getMain_tag())) {
				remove(artist);
			}
		}
	}
	
	/**
	 * Load object.
	 *
	 * @param file_path the file_path
	 * @return the artists to try
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
	 * Save object.
	 *
	 * @param file_path the file_path
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
