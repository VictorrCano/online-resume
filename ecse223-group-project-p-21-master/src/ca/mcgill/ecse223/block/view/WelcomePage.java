package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOUserMode;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;

import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.EnumSet;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

public class WelcomePage {

	public JFrame frame;
	private JTextField txtUsername_1;
	private JPasswordField pwdPassword_1;
	private JLabel lblErrormessage;


	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage window = new WelcomePage();
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
	public WelcomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Font projectfont = null;
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
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setBounds(100, 100, 624, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		JDesktopPane layeredPane_1 = new JDesktopPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane_1, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(layeredPane_1, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(89, Short.MAX_VALUE))
		);
		layeredPane_1.setLayout(null);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		
		JPanel panel_25 = new JPanel();
		panel_25.setVisible(false);
		
						
						lblErrormessage = new JLabel("");
						lblErrormessage.setForeground(new Color(0, 0, 51));
						lblErrormessage.setFont(new Font("Monospaced", Font.BOLD, 12));
						lblErrormessage.setText("errormessage");
						GroupLayout gl_panel_25 = new GroupLayout(panel_25);
						gl_panel_25.setHorizontalGroup(
							gl_panel_25.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_25.createSequentialGroup()
									.addComponent(lblErrormessage)
									.addContainerGap(198, Short.MAX_VALUE))
						);
						gl_panel_25.setVerticalGroup(
							gl_panel_25.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_25.createSequentialGroup()
									.addComponent(lblErrormessage)
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						);
						panel_25.setLayout(gl_panel_25);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane_1.setLayer(layeredPane, 50);
		layeredPane.setBounds(0, 0, 623, 458);
		layeredPane_1.add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("BLOCK 223");
		lblNewLabel.setBounds(178, 41, 278, 67);
		layeredPane.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(204, 204, 255));
		lblNewLabel.setFont(projectfont52);
				
				JPanel panel_22 = new JPanel();
				layeredPane.setLayer(panel_22, 1);
				panel_22.setBounds(136, 132, 354, 221);
				layeredPane.add(panel_22);
				panel_22.setBackground(new Color(0, 0, 102,200));
				layeredPane_1.setLayer(panel_22, 10);
				
				JPanel panel_23 = new JPanel();
				layeredPane.setLayer(panel_23, 2);
				panel_23.setBorder(new LineBorder(new Color(204, 204, 255)));
				panel_23.setBackground(new Color(0, 0, 102,0));
				layeredPane_1.setLayer(panel_23, 4);
				
				JPanel panel_24 = new JPanel();
				layeredPane.setLayer(panel_24, 2);
				panel_24.setBorder(new LineBorder(new Color(204, 204, 255)));
				panel_24.setBackground(new Color(0, 0, 153,0));
				layeredPane_1.setLayer(panel_24, 4);
				
				txtUsername_1 = new JTextField();
				layeredPane.setLayer(txtUsername_1, 2);
				txtUsername_1.setForeground(new Color(0, 0, 153));
				txtUsername_1.setFont(new Font("Charter", Font.PLAIN, 13));
				txtUsername_1.setColumns(10);
				
				JButton btnSignUp = new JButton("SIGN UP");
				layeredPane.setLayer(btnSignUp, 2);
				btnSignUp.setForeground(new Color(0, 0, 51));
				btnSignUp.setFont(projectfont15);
				
				btnSignUp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						SignUpPage signuppage= new SignUpPage();	//this is how you launch a different page
						signuppage.frame.setVisible(true);		//don't forget to set the frame etc. to public
					
					}
					
				});
				
				JButton btnLogin = new JButton("LOGIN");
				layeredPane.setLayer(btnLogin, 2);
				btnLogin.setForeground(new Color(0, 0, 51));
				btnLogin.setFont(projectfont15);
				
				btnLogin.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						
						String username=txtUsername_1.getText();	//getting text from the username text field
						String password=new String(pwdPassword_1.getPassword());	//getting text from the password field
						try {
							Block223Controller.login(username, password);	//calling controller to perform action associated with the method
							TOUserMode mode=Block223Controller.getUserMode();
							
							if (mode.getMode() == Mode.Design) {
								YouAreAnAdminPage adminPage = new YouAreAnAdminPage();
								frame.setVisible(false);
								adminPage.frame.setVisible(true);	
							}
							
							if (mode.getMode()==Mode.Play) {
								PlayerPage playerPage = new PlayerPage();
								frame.setVisible(false);
								playerPage.frame.setVisible(true);		
							}
						
						} catch (InvalidInputException e1) {
							lblErrormessage.setText(e1.getMessage());
							panel_25.setVisible(true);
						}
						
					}
				});
				
				pwdPassword_1 = new JPasswordField();
				layeredPane.setLayer(pwdPassword_1, 2);
				pwdPassword_1.setForeground(new Color(0, 0, 153));
				pwdPassword_1.setFont(new Font("Charter", Font.PLAIN, 13));
				pwdPassword_1.setColumns(10);

				GroupLayout gl_panel_22 = new GroupLayout(panel_22);
				gl_panel_22.setHorizontalGroup(
					gl_panel_22.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_22.createSequentialGroup()
							.addGap(132)
							.addComponent(btnLogin)
							.addContainerGap(149, Short.MAX_VALUE))
						.addGroup(gl_panel_22.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_panel_22.createParallelGroup(Alignment.LEADING)
								.addComponent(layeredPane_2, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_22.createSequentialGroup()
									.addGroup(gl_panel_22.createParallelGroup(Alignment.LEADING, false)
										.addComponent(panel_24, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(panel_23, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_22.createParallelGroup(Alignment.LEADING)
										.addComponent(txtUsername_1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
										.addComponent(pwdPassword_1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap())
						.addGroup(gl_panel_22.createSequentialGroup()
							.addGap(124)
							.addComponent(btnSignUp, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(140, Short.MAX_VALUE))
				);
				gl_panel_22.setVerticalGroup(
					gl_panel_22.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_22.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_22.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_23, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtUsername_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(gl_panel_22.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_24, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(pwdPassword_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(layeredPane_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSignUp)
							.addGap(8))
				);
				GroupLayout gl_layeredPane_2 = new GroupLayout(layeredPane_2);
				gl_layeredPane_2.setHorizontalGroup(
					gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				gl_layeredPane_2.setVerticalGroup(
					gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				layeredPane_2.setLayout(gl_layeredPane_2);
				
				JLabel lblNewLabel_2 = new JLabel("PASSWORD");
				lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
				panel_24.add(lblNewLabel_2);
				layeredPane.setLayer(lblNewLabel_2, 2);
				lblNewLabel_2.setForeground(new Color(204, 204, 255));
				lblNewLabel_2.setFont(projectfont15);
				
				JLabel lblNewLabel_1 = new JLabel("USERNAME");
				lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
				panel_23.add(lblNewLabel_1);
				layeredPane.setLayer(lblNewLabel_1, 2);
				lblNewLabel_1.setForeground(new Color(204, 204, 255));
				lblNewLabel_1.setFont(projectfont15);
				panel_22.setLayout(gl_panel_22);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 125, 122);
		panel.setBackground(Color.GREEN);
		layeredPane_1.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(124, 0, 125, 122);
		panel_1.setBackground(new Color(0, 0, 255));
		layeredPane_1.add(panel_1);
		
		JPanel panel_21 = new JPanel();
		panel_21.setBackground(new Color(0, 0, 102,200));
		layeredPane_1.setLayer(panel_21, 1);
		panel_21.setBounds(33, 26, 550, 426);
		layeredPane_1.add(panel_21);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(249, 0, 125, 122);
		panel_2.setBackground(Color.MAGENTA);
		layeredPane_1.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(374, 0, 125, 122);
		panel_3.setBackground(Color.YELLOW);
		layeredPane_1.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(498, 0, 125, 122);
		panel_4.setBackground(new Color(102, 255, 255));
		layeredPane_1.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 122, 125, 122);
		panel_5.setBackground(Color.YELLOW);
		layeredPane_1.add(panel_5);
		
		JPanel panel_7 = new JPanel();
		layeredPane_1.setLayer(panel_7, 3);
		panel_7.setBounds(249, 122, 125, 122);
		panel_7.setBackground(Color.GREEN);
		layeredPane_1.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		layeredPane_1.setLayer(panel_8, 3);
		panel_8.setBounds(374, 122, 125, 122);
		panel_8.setBackground(new Color(0, 0, 255));
		layeredPane_1.add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(498, 122, 125, 122);
		panel_9.setBackground(Color.MAGENTA);
		layeredPane_1.add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(0, 242, 125, 122);
		panel_10.setBackground(new Color(0, 0, 255));
		layeredPane_1.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		layeredPane_1.setLayer(panel_11, 3);
		panel_11.setBounds(124, 242, 125, 122);
		panel_11.setBackground(Color.MAGENTA);
		layeredPane_1.add(panel_11);
		
		JPanel panel_12 = new JPanel();
		layeredPane_1.setLayer(panel_12, 3);
		panel_12.setBounds(249, 242, 125, 122);
		panel_12.setBackground(Color.YELLOW);
		layeredPane_1.add(panel_12);
		
		JPanel panel_13 = new JPanel();
		layeredPane_1.setLayer(panel_13, 3);
		panel_13.setBounds(374, 242, 125, 122);
		panel_13.setBackground(new Color(102, 255, 255));
		layeredPane_1.add(panel_13);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(498, 242, 125, 122);
		panel_14.setBackground(Color.GREEN);
		layeredPane_1.add(panel_14);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBounds(0, 363, 125, 122);
		panel_15.setBackground(new Color(102, 255, 255));
		layeredPane_1.add(panel_15);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBounds(124, 363, 125, 122);
		panel_16.setBackground(Color.GREEN);
		layeredPane_1.add(panel_16);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBounds(249, 363, 125, 122);
		panel_17.setBackground(new Color(0, 0, 255));
		layeredPane_1.add(panel_17);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBounds(374, 363, 125, 122);
		panel_18.setBackground(Color.MAGENTA);
		layeredPane_1.add(panel_18);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBounds(498, 363, 125, 122);
		panel_19.setBackground(Color.YELLOW);
		layeredPane_1.add(panel_19);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBounds(0, 0, 10, 10);
		layeredPane_1.add(panel_20);
		
		JPanel panel_6 = new JPanel();
		layeredPane_1.setLayer(panel_6, 3);
		panel_6.setBackground(new Color(102, 255, 255));
		panel_6.setBounds(124, 122, 125, 122);
		layeredPane_1.add(panel_6);
		

		
	}
}


