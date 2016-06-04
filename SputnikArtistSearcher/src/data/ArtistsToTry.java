package data;

import java.io.Serializable;
import java.util.HashSet;

import page.SputnikAlbumPage;
import page.SputnikArtistPage;
import page.SputnikUserPage;

public class ArtistsToTry extends HashSet<ArtistToTry> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addArtistToTryFromSputnikUserPage(SputnikUserPage user_page, UnwantedArtists unwanted_artists, UnwantedArtists unwanted_tags) {
		
		FavoriteAlbumsPages favorite_album_pages = user_page.getFavoriteAlbumsPages();
		
		for (SputnikAlbumPage album_page : favorite_album_pages) {
			
			SputnikArtistPage album_artist_page = album_page.getArtistPage();
			
			if (unwanted_artists.contains(album_artist_page.getArtistName()) || unwanted_tags.contains(album_artist_page.getArtistTag())) {
				continue;
			}
			
			add(new ArtistToTry(album_artist_page.getArtistName(), album_page.getAlbumName(), album_artist_page.getArtistTag(), album_artist_page.getPage_url()));
		}
		
	}
	
}
