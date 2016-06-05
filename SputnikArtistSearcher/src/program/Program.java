package program;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;


import javax.swing.JOptionPane;
import java.awt.Desktop;

/**
 * 
 * Class used to launch the application.
 * 
 * @author Luis Campos
 *
 */

public class Program {

	private JFrame frame;
	private ProgramSession session;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program window = new Program();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Program() {
		session = new ProgramSession();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 497, 199);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel artist_name_label = new JLabel();
		artist_name_label.setBounds(10, 36, 325, 14);
		panel.add(artist_name_label);
		updateArtistNameLabel(artist_name_label);
		
		JLabel tag_label = new JLabel();
		updateTagLabel(tag_label);
		tag_label.setBounds(10, 50, 314, 14);
		panel.add(tag_label);
		
		JButton btnNextArtist = new JButton("Next Artist");
		btnNextArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.nextArtist();
				updateLabels(artist_name_label, tag_label);
			}
		});
		btnNextArtist.setBounds(10, 122, 118, 23);
		panel.add(btnNextArtist);
		
		JButton btnAddArtistTo = new JButton("Add Artist to CamposMusic List");
		btnAddArtistTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.addToCamposMusicList();
				updateLabels(artist_name_label, tag_label);
			}
		});
		btnAddArtistTo.setBounds(245, 122, 185, 23);
		panel.add(btnAddArtistTo);
		
		JButton btnAvoidTag = new JButton("Avoid Tag");
		btnAvoidTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.avoidTag();
				updateLabels(artist_name_label, tag_label);
			}
		});
		btnAvoidTag.setBounds(138, 122, 97, 23);
		panel.add(btnAvoidTag);
		

		
		JButton btnGoToYoutube = new JButton("Go To Youtube Video");
		btnGoToYoutube.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToYoutubeVideo();
			}
		});
		btnGoToYoutube.setBounds(10, 68, 195, 23);
		panel.add(btnGoToYoutube);
		
		JButton btnAddArtists = new JButton("Add Artists");
		btnAddArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = JOptionPane.showInputDialog(frame, "Please insert a Sputnik user ratings page URL.");
				try {
					session.addArtistsFromSputnikURL(str);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException|IllegalArgumentException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frame, "The URL you gave is not acceptable.");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddArtists.setBounds(334, 50, 110, 45);
		panel.add(btnAddArtists);
	}
	
	private void updateArtistNameLabel(JLabel artist_name_label) {
		artist_name_label.setText("Artist Name: " + session.getCurrentArtist().getArtist_name());
	}
	
	private void updateTagLabel(JLabel tag_name_label) {
		tag_name_label.setText("Tag: " + session.getCurrentArtist().getMain_tag());
	}
	
	private void updateLabels(JLabel artist_name_label, JLabel tag_name_label) {
		updateArtistNameLabel(artist_name_label);
		updateTagLabel(tag_name_label);
	}
	
	private void goToYoutubeVideo() {
		try {
			Desktop.getDesktop().browse(session.getCurrentArtist().getYoutube_url().toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
