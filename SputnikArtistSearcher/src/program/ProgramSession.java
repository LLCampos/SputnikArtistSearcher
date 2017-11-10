package program;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import data.ArtistToTry;
import data.ArtistsToTry;
import data.StringHashSetFromFile;
import page.SputnikUserPage;

/**
 * 
 * This class a Program Session.
 * 
 * @author Luis Campos
 *
 */
public class ProgramSession {
	
	/** The unwanted_artists. */
	private StringHashSetFromFile unwanted_artists;
	
	/** The unwanted_tags. */
	private StringHashSetFromFile unwanted_tags;
	
	/** The artists_to_try. */
	private ArtistsToTry artists_to_try;
	
	/** The settings. */
	private ProgramSettings settings;
	
	/**
	 * Starts a program session, loading all the necessary objects using the information in the settings.
	 */
	public ProgramSession() {
		settings = ProgramSettings.load();
		
		try {
			unwanted_artists = new StringHashSetFromFile(settings.getUnwanted_artist_path());
			unwanted_tags = new StringHashSetFromFile(settings.getUnwanted_tags_path());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			artists_to_try = ArtistsToTry.loadObject(settings.getArtiststotryfile_path());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The first artist of ArtistsToTry, which will be the one presented to the user.
	 * @return the artist to be presented to the user.
	 */
	public ArtistToTry getCurrentArtist() {
		try {
			return artists_to_try.get(0);
		} catch (IndexOutOfBoundsException e) {
			try {
				return new ArtistToTry("dummy", "dummy", "dummy", new URL("http://www.sputnikmusic.com/bands/The-Paper-Chase/11287/"));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
	
	
	/**
	 * Adds the artist currently being presented to the user to the list of unwanted artists.
	 */
	public void addCurrentArtistToUnwantedArtists() {
		unwanted_artists.add(getCurrentArtist().getArtist_name());
		try {
			unwanted_artists.save(settings.getUnwanted_artist_path());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the main tag of the artist currently being presented to the user to the list of unwanted tags.
	 */
	public void addToUnwantedTags() {
		unwanted_tags.add(getCurrentArtist().getMain_tag());
		try {
			unwanted_tags.save(settings.getUnwanted_tags_path());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Actions to take if the user decides to go to the next artist.
	 */
	public void nextArtist() {
		
		addCurrentArtistToUnwantedArtists();
		artists_to_try.remove(getCurrentArtist());
		artists_to_try.update(unwanted_artists, unwanted_tags);
		
		try {
			artists_to_try.saveObject(settings.getArtiststotryfile_path());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Actions to take if the user decides to avoid the tag of the current artist.
	 */
	public void avoidTag() {
		
		addToUnwantedTags();
		artists_to_try.remove(getCurrentArtist());
		artists_to_try.update(unwanted_artists, unwanted_tags);
		
		try {
			artists_to_try.saveObject(settings.getArtiststotryfile_path());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Actions to take if the user decides to add the artist currently being presented to the list
	 * of artists of the CamposMusic software, also developed by Luis Campos.
	 */
	public void addToCamposMusicList() {
		try {
			FileWriter writer = new FileWriter(settings.getCamposmusiclist_path(), true);
			writer.append(getCurrentArtist().getArtist_name() + " " + getCurrentArtist().getAlbum_name() + ",,\n");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		nextArtist();
	}
	
	/**
	 * Actions to take when the user submits an user's rating page URL to get new artists.
	 *
	 * @param url_str the url_str
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MalformedURLException the malformed url exception
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void addArtistsFromSputnikURL(String url_str) throws FileNotFoundException, IOException, MalformedURLException, IllegalArgumentException {
		SputnikUserPage user_page = new SputnikUserPage(url_str);
		artists_to_try.addArtistToTryFromSputnikUserPage(user_page, unwanted_artists, unwanted_tags);
		artists_to_try.saveObject(settings.getArtiststotryfile_path());
	}
}
