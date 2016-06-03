package program;

import page.SputnikAlbumPage;
import page.SputnikArtistPage;
import page.SputnikUserPage;

public class Tests {

	public static void main(String[] args) {
		// Test 1
		 /*
		SputnikArtistPage artist_page = new SputnikArtistPage("http://www.sputnikmusic.com/bands/Arctic-Monkeys/1847/");
		
		if (artist_page.getArtistName().equals("Arctic Monkeys")) {
			System.out.println("Test 1.1 passed.");
		} else {
			System.out.println("Test 1.1 failed.");
			System.out.println("Arctic Monkeys");
			System.out.println(artist_page.getArtistName());
		};
		
		if (artist_page.getArtistTag().equals("Indie Rock")) {
			System.out.println("Test 1.2 passed.");
		} else {
			System.out.println("Test 1.2 failed.");
			System.out.println("Indie Rock");
			System.out.println(artist_page.getArtistTag());
		};
		
		
		// Test 2
		
		SputnikAlbumPage album_page = new SputnikAlbumPage("http://www.sputnikmusic.com/review/70817/BUS-The-Unknown-Secretary/");
		
		SputnikArtistPage album_artist_page = album_page.getArtistPage();
		if (album_artist_page.getPage_url().toString().equals("http://www.sputnikmusic.com/bands/BUS/62292/")) {
			System.out.println("Test 2.1 passed.");
		} else {
			System.out.println("Test 2.1 failed.");
			System.out.println("http://www.sputnikmusic.com/bands/BUS/62292/");
			System.out.println(album_artist_page.getPage_url().toString());
		};
		
		if (album_page.getAlbumName().equals("The Unknown Secretary")) {
			System.out.println("Test 2.2 passed.");
		} else {
			System.out.println("Test 2.2 failed.");
			System.out.println("The Unknown Secretary");
			System.out.println(album_page.getAlbumName());
		};
		
		*/
		
		SputnikUserPage user_page = new SputnikUserPage("http://www.sputnikmusic.com/uservote.php?memberid=1043886");
	    for (SputnikAlbumPage album_page : user_page.getFavoriteAlbumsPages()) {
	    	System.out.println(album_page.getPage_url().toString());
	    };
	}

}
