
package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

//import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
//import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;

// import java.awt.Choice;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

public class ViewHallOfFamePage {

	public JFrame frame;
	JLabel lblErrorMessage;
	int level;
	JButton btnPrevious;
	JButton btnNewButton;
	int start=1;
	static int version;
	static String name;
	JLayeredPane layeredPane_3 = new JLayeredPane();
	JLabel lblQualifyer = new JLabel("");
	JLabel headerlabel=new JLabel("Position		Player		Score");
	Font projectfont=null;
	JPanel panel_3 = new JPanel();

	JLabel lbl1=new JLabel("1");
	JLabel lbl2=new JLabel("2");
	JLabel lbl3=new JLabel("3");
	JLabel lbl4=new JLabel("4");
	JLabel lbl5=new JLabel("5");
	JLabel lbl6=new JLabel("6");
	JLabel lbl7=new JLabel("7");
	JLabel lbl8=new JLabel("8");
	JLabel lbl9=new JLabel("9");
	JLabel lbl10=new JLabel("10");





	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHallOfFamePage window = new ViewHallOfFamePage(version, name);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	private void makeFont() {
		try {
			projectfont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/ARCADECLASSIC.TTF"));
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Font projectfont52 = projectfont.deriveFont(52f);
		Font projectfont15 = projectfont.deriveFont(15f);
		Font projectfont12 = projectfont.deriveFont(12f);
		Font projectfont32 = projectfont.deriveFont(32f);
		Font projectfont22 = projectfont.deriveFont(22f);
	}


	private void refresh1() {
		//Refresh for entry from PlayerPage/gamePlayPage
		Font projectfont52 = projectfont.deriveFont(52f);
		Font projectfont15 = projectfont.deriveFont(15f);
		Font projectfont12 = projectfont.deriveFont(12f);
		Font projectfont32 = projectfont.deriveFont(32f);
		Font projectfont22 = projectfont.deriveFont(22f);

		//layeredPane_3.removeAll();
		//panel_3.add(headerlabel);

		TOHallOfFame hof;
		lblQualifyer.setText("RANKING");
		try {
			panel_3.removeAll();
			//panel_3.repaint();
			
			hof = Block223Controller.getHallOfFameWithoutSelecting(name,start, (start+10));

			TOHallOfFameEntry entry1=null;
			TOHallOfFameEntry entry2=null;
			TOHallOfFameEntry entry3=null;
			TOHallOfFameEntry entry4=null;
			TOHallOfFameEntry entry5=null;
			TOHallOfFameEntry entry6=null;
			TOHallOfFameEntry entry7=null;
			TOHallOfFameEntry entry8=null;
			TOHallOfFameEntry entry9=null;
			TOHallOfFameEntry entry10=null;
			
			lbl1.setText("");
			lbl2.setText("");
			lbl3.setText("");
			lbl4.setText("");
			lbl5.setText("");
			lbl6.setText("");
			lbl7.setText("");
			lbl8.setText("");
			lbl9.setText("");
			lbl10.setText("");

			int h=hof.numberOfEntries()-1;
			//lbl1
			if (hof.numberOfEntries()>0) {
				entry1 = hof.getEntry(h);
				h--;
			}
			if (hof.numberOfEntries()>1) {
				entry2 = hof.getEntry(h);
				h--;
			}

			if (hof.numberOfEntries()>2) {
				entry3 = hof.getEntry(h);
				h--;
			}

			if (hof.numberOfEntries()>3) {
				entry4 = hof.getEntry(h);
				h--;
			}

			if (hof.numberOfEntries()>4) {
				entry5 = hof.getEntry(h);
				h--;
			}

			if (hof.numberOfEntries()>5) {
				entry6 = hof.getEntry(h);
				h--;
			}
			if (hof.numberOfEntries()>6) {
				entry7 = hof.getEntry(h);
				h--;
			}
			if (hof.numberOfEntries()>7) {
				entry8 = hof.getEntry(h);
				h--;
			}
			if (hof.numberOfEntries()>8) {
				entry9 = hof.getEntry(h);
				h--;
			}
			if (hof.numberOfEntries()>9) {
				entry10 = hof.getEntry(h);
			}

			if (entry1!=null)
				lbl1.setText(entry1.getPosition()+"     "+entry1.getPlayername()+"      "+entry1.getScore());

			if (entry2!=null)
				lbl2.setText(entry2.getPosition()+"     "+entry2.getPlayername()+"      "+entry2.getScore());

			if (entry3!=null)
				lbl3.setText(entry3.getPosition()+"     "+entry3.getPlayername()+"     "+entry3.getScore());

			if (entry4!=null)
				lbl4.setText(entry4.getPosition()+"     "+entry4.getPlayername()+"      "+entry4.getScore());

			if (entry5!=null)
				lbl5.setText(entry5.getPosition()+"     "+entry5.getPlayername()+"     "+entry5.getScore());

			if (entry6!=null)
				lbl6.setText(entry6.getPosition()+"     "+entry6.getPlayername()+"     "+entry6.getScore());

			if (entry7!=null)
				lbl7.setText(entry7.getPosition()+"     "+entry7.getPlayername()+"     "+entry7.getScore());

			if (entry8!=null)
				lbl8.setText(entry8.getPosition()+"     "+entry8.getPlayername()+"     "+entry8.getScore());

			if (entry9!=null)
				lbl9.setText(entry9.getPosition()+"     "+entry9.getPlayername()+"     "+entry9.getScore());

			if (entry10!=null)
				lbl10.setText(entry10.getPosition()+"     "+entry10.getPlayername()+"     "+entry10.getScore());

			panel_3.add(lbl1);
			panel_3.add(lbl2);
			panel_3.add(lbl3);
			panel_3.add(lbl4);
			panel_3.add(lbl5);
			panel_3.add(lbl6);
			panel_3.add(lbl7);
			panel_3.add(lbl8);
			panel_3.add(lbl9);
			panel_3.add(lbl10);

		} catch (InvalidInputException e) {
			lblErrorMessage.setText(e.getMessage());
		}
		panel_3.repaint();	
	}

	private void refresh2() {
		Font projectfont52 = projectfont.deriveFont(52f);
		Font projectfont15 = projectfont.deriveFont(15f);
		Font projectfont12 = projectfont.deriveFont(12f);
		Font projectfont32 = projectfont.deriveFont(32f);
		Font projectfont22 = projectfont.deriveFont(22f);

		//layeredPane_3.removeAll();
		//panel_3.add(headerlabel);

		TOHallOfFame hof;
		lblQualifyer.setText("RANKING");
		try {
			panel_3.removeAll();
			//panel_3.repaint();
			hof = Block223Controller.getHallOfFameWithMostRecentEntryWithoutSelecting(name,10);

			TOHallOfFameEntry entry1=null;
			TOHallOfFameEntry entry2=null;
			TOHallOfFameEntry entry3=null;
			TOHallOfFameEntry entry4=null;
			TOHallOfFameEntry entry5=null;
			TOHallOfFameEntry entry6=null;
			TOHallOfFameEntry entry7=null;
			TOHallOfFameEntry entry8=null;
			TOHallOfFameEntry entry9=null;
			TOHallOfFameEntry entry10=null;
			
			lbl1.setText("");
			lbl2.setText("");
			lbl3.setText("");
			lbl4.setText("");
			lbl5.setText("");
			lbl6.setText("");
			lbl7.setText("");
			lbl8.setText("");
			lbl9.setText("");
			lbl10.setText("");

			int h=hof.numberOfEntries()-1;
			//lbl1
			if (hof.numberOfEntries()>0) {
				entry1 = hof.getEntry(h);
				h--;
			}
			if (hof.numberOfEntries()>1) {
				entry2 = hof.getEntry(h);
				h--;
			}

			if (hof.numberOfEntries()>2) {
				entry3 = hof.getEntry(h);
				h--;
			}

			if (hof.numberOfEntries()>3) {
				entry4 = hof.getEntry(h);
				h--;
			}

			if (hof.numberOfEntries()>4) {
				entry5 = hof.getEntry(h);
				h--;
			}

			if (hof.numberOfEntries()>5) {
				entry6 = hof.getEntry(h);
				h--;
			}
			if (hof.numberOfEntries()>6) {
				entry7 = hof.getEntry(h);
				h--;
			}
			if (hof.numberOfEntries()>7) {
				entry8 = hof.getEntry(h);
				h--;
			}
			if (hof.numberOfEntries()>8) {
				entry9 = hof.getEntry(h);
				h--;
			}
			if (hof.numberOfEntries()>9) {
				entry10 = hof.getEntry(h);
			}

			if (entry1!=null)
				lbl1.setText(entry1.getPosition()+"     "+entry1.getPlayername()+"     "+entry1.getScore());

			if (entry2!=null)
				lbl2.setText(entry2.getPosition()+"     "+entry2.getPlayername()+"     "+entry2.getScore());

			if (entry3!=null)
				lbl3.setText(entry3.getPosition()+"     "+entry3.getPlayername()+"     "+entry3.getScore());

			if (entry4!=null)
				lbl4.setText(entry4.getPosition()+"     "+entry4.getPlayername()+"     "+entry4.getScore());

			if (entry5!=null)
				lbl5.setText(entry5.getPosition()+"     "+entry5.getPlayername()+"     "+entry5.getScore());

			if (entry6!=null)
				lbl6.setText(entry6.getPosition()+"     "+entry6.getPlayername()+"     "+entry6.getScore());

			if (entry7!=null)
				lbl7.setText(entry7.getPosition()+"     "+entry7.getPlayername()+"     "+entry7.getScore());

			if (entry8!=null)
				lbl8.setText(entry8.getPosition()+"      "+entry8.getPlayername()+"     "+entry8.getScore());

			if (entry9!=null)
				lbl9.setText(entry9.getPosition()+"     "+entry9.getPlayername()+"     "+entry9.getScore());

			if (entry10!=null)
				lbl10.setText(entry10.getPosition()+"     "+entry10.getPlayername()+"     "+entry10.getScore());

			panel_3.add(lbl1);
			panel_3.add(lbl2);
			panel_3.add(lbl3);
			panel_3.add(lbl4);
			panel_3.add(lbl5);
			panel_3.add(lbl6);
			panel_3.add(lbl7);
			panel_3.add(lbl8);
			panel_3.add(lbl9);
			panel_3.add(lbl10);
			
			btnNewButton.setVisible(false);
			btnPrevious.setVisible(false);
			

		} catch (InvalidInputException e) {
			lblErrorMessage.setText(e.getMessage());
		}
		panel_3.repaint();	
	}

	/**
	 * Create the application.
	 */
	public ViewHallOfFamePage(int version, String name) {
		this.name=name;
		this.version=version;
		initialize();
		if (this.version==1)
			refresh1();
		if (this.version==2)
			refresh2();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		makeFont();
		Font projectfont52 = projectfont.deriveFont(52f);
		Font projectfont15 = projectfont.deriveFont(15f);
		Font projectfont12 = projectfont.deriveFont(12f);
		Font projectfont32 = projectfont.deriveFont(32f);

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 51));




		/* The comboBox needs to have a list of all the names of the existing games. */

		JLabel lblGame = new JLabel("Game");
		lblGame.setHorizontalAlignment(SwingConstants.CENTER);

		lblGame.setText(name);

		lblGame.setFont(projectfont52);
		lblGame.setForeground(new Color(240, 248, 255));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 255));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 0));

		JDesktopPane layeredPane_1 = new JDesktopPane();

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane_1.setLayer(layeredPane, 50);
		layeredPane.setBounds(0, 0, 624, 485);
		layeredPane_1.add(layeredPane);

		lblErrorMessage = new JLabel("");
		lblErrorMessage.setForeground(new Color(204, 255, 255));
		lblErrorMessage.setFont(new Font("Monospaced", Font.BOLD, 12));

		JLayeredPane layeredPane_2 = new JLayeredPane();

		JLabel lblHallOfFame = new JLabel("HALL OF FAME");
		lblHallOfFame.setForeground(Color.CYAN);
		lblHallOfFame.setFont(projectfont32);

		JPanel panel_18 = new JPanel();
		panel_18.setBackground(Color.CYAN);

		JPanel panel_19 = new JPanel();
		panel_19.setBackground(Color.GREEN);

		JPanel panel_20 = new JPanel();
		panel_20.setBackground(new Color(153, 50, 204));

		JPanel panel_21 = new JPanel();
		panel_21.setBackground(Color.MAGENTA);

		JPanel panel_22 = new JPanel();
		panel_22.setBackground(new Color(255, 255, 0));

		JPanel panel_23 = new JPanel();
		panel_23.setBackground(Color.CYAN);

		JPanel panel_24 = new JPanel();
		panel_24.setBackground(Color.GREEN);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.CYAN);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(153, 50, 204));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.YELLOW);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.MAGENTA);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.CYAN);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.GREEN);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.MAGENTA);

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(153, 50, 204));

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.MAGENTA);

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.YELLOW);

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.CYAN);

		JPanel panel_16 = new JPanel();
		panel_16.setBackground(Color.GREEN);

		JPanel panel_17 = new JPanel();
		panel_17.setBackground(new Color(153, 50, 204));

		JPanel panel_25 = new JPanel();
		panel_25.setBackground(Color.CYAN);

		JPanel panel_26 = new JPanel();
		panel_26.setBackground(new Color(153, 50, 204));

		JPanel panel_28 = new JPanel();
		panel_28.setBackground(Color.YELLOW);

		JPanel panel_30 = new JPanel();
		panel_30.setBackground(Color.GREEN);

		JPanel panel_32 = new JPanel();
		panel_32.setBackground(Color.MAGENTA);

		JPanel panel_34 = new JPanel();
		panel_34.setBackground(Color.CYAN);

		JPanel panel_27 = new JPanel();
		panel_27.setBackground(Color.YELLOW);

		JPanel panel_29 = new JPanel();
		panel_29.setBackground(new Color(153, 50, 204));

		JPanel panel_31 = new JPanel();
		panel_31.setBackground(Color.GREEN);

		JPanel panel_33 = new JPanel();
		panel_33.setBackground(Color.YELLOW);

		JPanel panel_35 = new JPanel();
		panel_35.setBackground(new Color(153, 50, 204));

		JPanel panel_36 = new JPanel();
		panel_36.setBackground(Color.MAGENTA);

		JPanel panel_37 = new JPanel();
		panel_37.setBackground(Color.CYAN);

		JPanel panel_38 = new JPanel();
		panel_38.setBackground(Color.GREEN);

		JPanel panel_39 = new JPanel();
		panel_39.setBackground(Color.YELLOW);

		JLayeredPane layeredPane_3 = new JLayeredPane();
		layeredPane_3.setForeground(new Color(100, 149, 237));
		layeredPane_3.setBackground(new Color(100, 149, 237));

		btnNewButton = new JButton("NEXT");
		btnNewButton.setFont(projectfont15);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start=start+11;
				refresh1();
			}
		});

		btnPrevious = new JButton("PREVIOUS");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(start!=1)
					start=start-11;
				refresh1();
			}
		});
		btnPrevious.setFont(projectfont15);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PlayerPage pplayer = new PlayerPage();
				pplayer.frame.setVisible(true);
				frame.setVisible(false);

			}
		});
		btnExit.setFont(projectfont15);
		lblQualifyer.setHorizontalAlignment(SwingConstants.CENTER);

		lblQualifyer.setFont(projectfont12);
		lblQualifyer.setForeground(Color.WHITE);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_26, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_28, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_30, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_32, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_34, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel_18, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel_19, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(panel_20, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
												.addComponent(panel_21, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
											.addComponent(panel_22, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
										.addComponent(panel_23, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_17, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_14, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_15, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_16, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_24, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblGame, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
									.addGap(19))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblQualifyer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblHallOfFame, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(108))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(layeredPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(layeredPane_3, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblErrorMessage, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(btnPrevious)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnExit)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))))
									.addGap(81)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_27, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_31, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_35, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_36, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_39, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_29, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_33, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_37, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_38, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addGap(164))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_28, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_30, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_32, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_34, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_27, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
								.addComponent(layeredPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_29, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(panel_35, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
												.addComponent(panel_33, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(panel_37, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
												.addComponent(panel_36, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(panel_39, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
												.addComponent(panel_38, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
										.addComponent(panel_31, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(332)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton)
										.addComponent(btnExit)
										.addComponent(btnPrevious)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_26, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(lblGame)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblHallOfFame)
									.addGap(1)
									.addComponent(lblQualifyer)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(layeredPane_3, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblErrorMessage, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_24, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_18, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel_19, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(panel_14, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(panel_15, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(panel_16, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(panel_17, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(panel_20, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(panel_21, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(panel_22, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(panel_23, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))))))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		layeredPane_3.setLayout(new GridLayout(0, 1, 0, 0));

		panel_3 = new JPanel();
		layeredPane_3.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lbl1 = new JLabel("B");
		panel_3.add(lbl1);

		JLabel lbl2 = new JLabel("D");
		panel_3.add(lbl2);

		JLabel lbl3 = new JLabel("E");
		panel_3.add(lbl3);

		JLabel lbl4 = new JLabel("G");
		panel_3.add(lbl4);

		JLabel lbl5 = new JLabel("C");
		panel_3.add(lbl5);

		JLabel lbl6 = new JLabel("I");
		panel_3.add(lbl6);

		JLabel lbl7 = new JLabel("F");
		panel_3.add(lbl7);

		JLabel lbl8 = new JLabel("D");
		panel_3.add(lbl8);

		JLabel lbl9 = new JLabel("H");
		panel_3.add(lbl9);

		JLabel lbl10 = new JLabel("A");
		panel_3.add(lbl10);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(200, 200, 784, 681);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



	}
}

