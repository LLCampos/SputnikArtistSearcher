package program;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.MalformedURLException;

import data.ArtistToTry;
import data.ArtistsToTry;
import data.Unwanteds;
import page.SputnikUserPage;

public class ProgramSession {
	
	private Unwanteds unwanted_artists;
	private Unwanteds unwanted_tags;
	private ArtistsToTry artists_to_try;
	private ProgramSettings settings;
	
	public ProgramSession() {
		settings = ProgramSettings.load();
		
		try {
			unwanted_artists = new Unwanteds(settings.getUnwanted_artist_path());
			unwanted_tags = new Unwanteds(settings.getUnwanted_tags_path());
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		
		artists_to_try = ArtistsToTry.loadObject(settings.getArtiststotryfile_path());
	}
	
	public ArtistToTry getCurrentArtist() {
		return artists_to_try.get(0);
	}
	
	public void addCurrentArtistToUnwantedArtists() {
		unwanted_artists.add(getCurrentArtist().getArtist_name());
		unwanted_artists.save(settings.getUnwanted_artist_path());
	}
	
	public void addToUnwantedTags() {
		unwanted_tags.add(getCurrentArtist().getMain_tag());
		unwanted_tags.save(settings.getUnwanted_tags_path());
	}
	
	public void nextArtist() {
		addCurrentArtistToUnwantedArtists();
		artists_to_try.remove(getCurrentArtist());
		artists_to_try.update(unwanted_artists, unwanted_tags);
		artists_to_try.saveObject(settings.getArtiststotryfile_path());
	}
	
	public void avoidTag() {
		addToUnwantedTags();
		artists_to_try.remove(getCurrentArtist());
		artists_to_try.update(unwanted_artists, unwanted_tags);
		artists_to_try.saveObject(settings.getArtiststotryfile_path());		
	}
	
	public void addToCamposMusicList() {
		try {
			FileWriter writer = new FileWriter(settings.getCamposmusiclist_path(), true);
			writer.append(getCurrentArtist().getArtist_name() + " " + getCurrentArtist().getBest_album_name() + ",,\n");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		nextArtist();
	}
	
	public Boolean addArtistsFromSputnikURL(String url_str) {
		try {
			SputnikUserPage user_page = new SputnikUserPage(url_str);
			artists_to_try.addArtistToTryFromSputnikUserPage(user_page, unwanted_artists, unwanted_tags);
			artists_to_try.saveObject(settings.getArtiststotryfile_path());
			return true;
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		
	}
}
