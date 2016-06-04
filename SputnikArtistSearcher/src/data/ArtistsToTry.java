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

public class ArtistsToTry extends ArrayList<ArtistToTry> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addArtistToTryFromSputnikUserPage(SputnikUserPage user_page, UnwantedArtists unwanted_artists, UnwantedTags unwanted_tags) {
		
		FavoriteAlbumsPages favorite_album_pages = user_page.getFavoriteAlbumsPages(unwanted_artists);
		
		for (SputnikAlbumPage album_page : favorite_album_pages) {
			
			SputnikArtistPage album_artist_page = album_page.getArtistPage();
			
			if (unwanted_artists.contains(album_artist_page.getArtistName()) || unwanted_tags.contains(album_artist_page.getArtistTag())) {
				continue;
			}
			
			add(new ArtistToTry(album_artist_page.getArtistName(), album_page.getAlbumName(), album_artist_page.getArtistTag(), album_artist_page.getPage_url()));
		}
	}
	
	public void update(UnwantedArtists unwanted_artists, UnwantedTags unwanted_tags) {
		
		 ArtistsToTry list = (ArtistsToTry) this.clone();
		
		for (ArtistToTry artist : list) {
			if (unwanted_artists.contains(artist.getArtist_name()) || unwanted_tags.contains(artist.getMain_tag())) {
				remove(artist);
			}
		}
	}
	
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
