package program;

import java.io.IOException;

public class updateSettings {

	public static void main(String[] args) {
		ProgramSettings settings = ProgramSettings.load();
		
		String base_url = "C:\\Users\\Luis\\Google Drive\\Backup Ficheiros Programas Musica\\";
		
		try {
			settings.setArtiststotryfile_path(base_url + "ArtistsToTry.ser");
			settings.setCamposmusiclist_path(base_url + "Lista Musica.txt");
			settings.setUnwanted_artist_path(base_url + "UnwantedArtists.txt");
			settings.setUnwanted_tags_path(base_url + "UnwantedTags.txt");
			settings.save();
		} catch (ClassNotFoundException | IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}

	}

}
