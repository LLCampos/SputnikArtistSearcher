package program;

import data.ArtistToTry;
import data.ArtistsToTry;
import data.UnwantedArtists;
import data.UnwantedTags;
import page.SputnikUserPage;

public class ProgramSession {
	
	private UnwantedArtists unwanted_artists;
	private UnwantedTags unwanted_tags;
	private ArtistsToTry artists_to_try;
	
	public ProgramSession() {
		ProgramSettings settings = ProgramSettings.load();
		
		unwanted_artists = new UnwantedArtists(settings.getUnwanted_artist_path());
		unwanted_tags = new UnwantedTags(settings.getUnwanted_tags_path());
		artists_to_try = ArtistsToTry.loadObject(settings.getArtiststotryfile_path());
	}
}
