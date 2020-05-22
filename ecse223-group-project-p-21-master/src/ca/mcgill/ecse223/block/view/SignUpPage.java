package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.LayoutStyle.ComponentPlacement;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SignUpPage {

	public JFrame frame;
	public JTextField txtUsername;
	public JPasswordField pwdPassword;
	public JPasswordField pwdPassword_1;
	public JLabel lblErrorMessage;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					SignUpPage window = new SignUpPage();
					window.frame.setVisible(true);
					window.frame.setPreferredSize(new Dimension(400, 300));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUpPage() {
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
		Font projectfont24 = projectfont.deriveFont(24f);
		Font projectfont10 = projectfont.deriveFont(10f);
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setBounds(100, 100, 624, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSignUp = new JLabel("SIGN UP");
		lblSignUp.setForeground(new Color(153, 204, 204));
		lblSignUp.setFont(projectfont52);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(projectfont15);
		lblUsername.setForeground(new Color(153, 204, 204));
		lblUsername.setBackground(Color.WHITE);
		
		JLabel lblPlayerPassword = new JLabel("Player Password");
		lblPlayerPassword.setForeground(new Color(153, 204, 204));
		lblPlayerPassword.setFont(projectfont15);
		
		JLabel lblAdminPassword = new JLabel("Admin Password");
		lblAdminPassword.setFont(projectfont15);
		lblAdminPassword.setForeground(new Color(153, 204, 204));
		
		txtUsername = new JTextField();
		txtUsername.setForeground(new Color(0, 0, 153));
		txtUsername.setFont(new Font("Charter", Font.BOLD, 13));
		txtUsername.setText("");
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setForeground(new Color(0, 0, 153));
		pwdPassword.setEchoChar('*');
		pwdPassword.setText("");
		
		pwdPassword_1 = new JPasswordField();
		pwdPassword_1.setForeground(new Color(0, 0, 153));
		pwdPassword_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		pwdPassword_1.setEchoChar('*');
		pwdPassword_1.setText("");
		
		JLabel lblNewLabel = new JLabel("Optional");
		lblNewLabel.setFont(projectfont10);
		lblNewLabel.setForeground(new Color(153, 204, 204));
		
		JButton btnCreateAccount = new JButton("CREATE ACCOUNT");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username= txtUsername.getText();
				String playerpassword=new String(pwdPassword.getPassword());
				String adminpassword=new String(pwdPassword_1.getPassword());
				
				try {
					
					Block223Controller.register(username, playerpassword, adminpassword);
					frame.setVisible(false);
				} 
				catch (InvalidInputException e1) {
					lblErrorMessage.setText(e1.getMessage());
					lblErrorMessage.setVisible(true);
				}
				

			}
		});
		btnCreateAccount.setForeground(new Color(0, 0, 102));
		btnCreateAccount.setBackground(new Color(153, 204, 204));
		btnCreateAccount.setFont(projectfont15);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		btnCancel.setFont(projectfont15);
		btnCancel.setForeground(new Color(0, 0, 102));
		btnCancel.setBackground(new Color(153, 204, 204));
		
		lblErrorMessage = new JLabel("errormessage");
		lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMessage.setVerticalAlignment(SwingConstants.TOP);
		lblErrorMessage.setForeground(new Color(153, 204,204));
		lblErrorMessage.setFont(new Font("Monospaced", Font.BOLD, 13));
		lblErrorMessage.setVisible(false);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(217)
							.addComponent(btnCreateAccount))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(208)
							.addComponent(lblSignUp))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(144)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(45)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblAdminPassword)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(22)
													.addComponent(lblUsername)))
											.addGap(21))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblPlayerPassword)
											.addGap(18)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(pwdPassword_1)
										.addComponent(txtUsername)
										.addComponent(pwdPassword, 170, 170, Short.MAX_VALUE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(237)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(87)
							.addComponent(lblErrorMessage, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(104, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(lblSignUp)
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pwdPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPlayerPassword))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdminPassword)
						.addComponent(pwdPassword_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblErrorMessage, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(btnCreateAccount, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}