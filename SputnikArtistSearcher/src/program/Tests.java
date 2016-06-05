package program;

import java.net.MalformedURLException;
import java.net.URL;

import data.ArtistToTry;
import data.Unwanteds;
import page.SputnikAlbumPage;
import page.SputnikArtistPage;

public class Tests {

	public static void main(String[] args) {
		
		// Test 1
		SputnikArtistPage artist_page =  null;
		
		try {
			artist_page = new SputnikArtistPage("http://www.sputnikmusic.com/bands/Arctic-Monkeys/1847/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (artist_page.getArtistName().equals("arctic monkeys")) {
			System.out.println("Test 1.1 passed.");
		} else {
			System.out.println("Test 1.1 failed.");
			System.out.println("Arctic Monkeys");
			System.out.println(artist_page.getArtistName());
		};
		
		if (artist_page.getArtistTag().equals("indie rock")) {
			System.out.println("Test 1.2 passed.");
		} else {
			System.out.println("Test 1.2 failed.");
			System.out.println("Indie Rock");
			System.out.println(artist_page.getArtistTag());
		};
		
		
		// Test 2
		SputnikAlbumPage album_page = null;
		
		try {
			album_page = new SputnikAlbumPage("http://www.sputnikmusic.com/review/70817/BUS-The-Unknown-Secretary/");
		} catch (Exception e) {
			
		}
		
		SputnikArtistPage album_artist_page = album_page.getArtistPage();
		if (album_artist_page.getPage_url().toString().equals("http://www.sputnikmusic.com//bands/BUS/62292/")) {
			System.out.println("Test 2.1 passed.");
		} else {
			System.out.println("Test 2.1 failed.");
			System.out.println("http://www.sputnikmusic.com//bands/BUS/62292/");
			System.out.println(album_artist_page.getPage_url().toString());
		};
		
		if (album_page.getAlbumName().equals("The Unknown Secretary")) {
			System.out.println("Test 2.2 passed.");
		} else {
			System.out.println("Test 2.2 failed.");
			System.out.println("The Unknown Secretary");
			System.out.println(album_page.getAlbumName());
		};
		
		// Test 3
		try {
			ArtistToTry artist = new ArtistToTry("The paper chase", "now you are one of us", "rock", new URL("http://www.sputnikmusic.com/bands/The-Paper-Chase/11287/"));
			
			if (artist.getYoutube_url().toString().equals("https://www.youtube.com/results?search_query=the+paper+chase+now+you+are+one+of+us")) {
				System.out.println("Test 3 passed.");
			} else {
				System.out.println("https://www.youtube.com/results?search_query=the+paper+chase+now+you+are+one+of+us");
				System.out.println(artist.getYoutube_url().toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		// Test 4
		try {
			Unwanteds unwanted_artists = new Unwanteds("program_files/UnwantedArtists.txt");
			System.out.println(unwanted_artists.contains("arcade fire") ? "Test 4.1 Passed." : "Test 4.1 Failed.");
			System.out.println(unwanted_artists.contains("afasdfsda") ? "Test 4.2 Failed." : "Test 4.2 Passed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
