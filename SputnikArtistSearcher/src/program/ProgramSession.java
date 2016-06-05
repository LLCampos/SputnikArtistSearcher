package program;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import data.ArtistToTry;
import data.ArtistsToTry;
import data.StringHashSetFromFile;
import page.SputnikUserPage;

public class ProgramSession {
	
	private StringHashSetFromFile unwanted_artists;
	private StringHashSetFromFile unwanted_tags;
	private ArtistsToTry artists_to_try;
	private ProgramSettings settings;
	
	public ProgramSession() {
		settings = ProgramSettings.load();
		
		try {
			unwanted_artists = new StringHashSetFromFile(settings.getUnwanted_artist_path());
			unwanted_tags = new StringHashSetFromFile(settings.getUnwanted_tags_path());
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		
		try {
			artists_to_try = ArtistsToTry.loadObject(settings.getArtiststotryfile_path());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArtistToTry getCurrentArtist() {
		return artists_to_try.get(0);
	}
	
	public void addCurrentArtistToUnwantedArtists() {
		unwanted_artists.add(getCurrentArtist().getArtist_name());
		try {
			unwanted_artists.save(settings.getUnwanted_artist_path());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addToUnwantedTags() {
		unwanted_tags.add(getCurrentArtist().getMain_tag());
		try {
			unwanted_tags.save(settings.getUnwanted_tags_path());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nextArtist() {
		addCurrentArtistToUnwantedArtists();
		artists_to_try.remove(getCurrentArtist());
		artists_to_try.update(unwanted_artists, unwanted_tags);
		try {
			artists_to_try.saveObject(settings.getArtiststotryfile_path());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void avoidTag() {
		addToUnwantedTags();
		artists_to_try.remove(getCurrentArtist());
		artists_to_try.update(unwanted_artists, unwanted_tags);
		try {
			artists_to_try.saveObject(settings.getArtiststotryfile_path());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
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
	
	public Boolean addArtistsFromSputnikURL(String url_str) {
		try {
			SputnikUserPage user_page = new SputnikUserPage(url_str);
			artists_to_try.addArtistToTryFromSputnikUserPage(user_page, unwanted_artists, unwanted_tags);
			try {
				artists_to_try.saveObject(settings.getArtiststotryfile_path());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		
	}
}
