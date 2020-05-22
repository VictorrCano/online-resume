package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;

import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.SystemColor;

public class PopUpWindowUpdate {

	public JFrame frame;
	public int blockId;
	private JTextField BlueValue;
	private JTextField textField_1;
	private JTextField GreenValue;
	private JTextField RedValue;
	private JLabel lblErrormessage;

	/**
	 * Launch the application.
	 */
	public static void PopUpWindowUpdate(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpWindowUpdate window = new PopUpWindowUpdate(0);
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
	public PopUpWindowUpdate(int blockId) {
		this.blockId = blockId;
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

		Font projectfont15 = projectfont.deriveFont(15f);
		Font projectfont24 = projectfont.deriveFont(24f);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 326, 329);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(layeredPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 290, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
		);
		
		JLabel label_2 = new JLabel("POINTS");
		label_2.setForeground(new Color(0, 0, 51));
		label_2.setFont(projectfont15);
		
		JLabel lblUpdateBlock = new JLabel("UPDATE BLOCK");
		lblUpdateBlock.setForeground(new Color(0, 0, 51));
		lblUpdateBlock.setFont(projectfont24);
		lblUpdateBlock.setBackground(new Color(224, 255, 255));
		
		TOBlock block = null;
		
		try {
			block=Block223Controller.getBlockOfCurrentDesignableGame(blockId);
		} catch (InvalidInputException e1) {
			lblErrormessage.setText(e1.getMessage());
			lblErrormessage.setVisible(true);
		}
		
		JLabel label = new JLabel("RED");
		label.setForeground(Color.RED);
		label.setFont(projectfont15);
		RedValue = new JTextField(""+block.getRed());
		
		
		RedValue.setForeground(Color.BLACK);
		RedValue.setColumns(10);
		
		JLabel label_3 = new JLabel("BLUE");
		label_3.setForeground(Color.BLUE);
		label_3.setFont(projectfont15);
		BlueValue = new JTextField(""+block.getBlue());
		BlueValue.setForeground(Color.BLACK);
		BlueValue.setColumns(10);
		
		JLabel label_4 = new JLabel("GREEN");
		label_4.setForeground(Color.GREEN);
		label_4.setFont(projectfont15);
		GreenValue = new JTextField(""+block.getGreen());
		
		
		GreenValue.setForeground(Color.BLACK);
		GreenValue.setColumns(10);
		textField_1 = new JTextField(""+block.getPoints());
		
		
		textField_1.setForeground(Color.BLACK);
		textField_1.setColumns(10);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String redvalue = RedValue.getText();
				String greenvalue = GreenValue.getText();
				String bluevalue = BlueValue.getText();		
				String pointvalue = textField_1.getText();
				int red;
				int blue;
				int green;
				int points;
				
				if (redvalue.equals("")) { red=-1;}
				else {red = Integer.parseInt(redvalue);}
				
				if (greenvalue.equals("")) { green=-1;}
				else {green = Integer.parseInt(greenvalue);}
				
				if (bluevalue.equals("")) { blue=-1;}
				else {blue = Integer.parseInt(bluevalue);}
				
				if (pointvalue.equals("")) {points=-1;}
				else {points = Integer.parseInt(pointvalue);}
				
				try {
					Block223Controller.updateBlock(blockId, red, green, blue, points);
					frame.setVisible(false);
				} catch (InvalidInputException e1) {
					lblErrormessage.setText(e1.getMessage());
					lblErrormessage.setVisible(true);
				}
				
			}
		});
		btnUpdate.setFont(projectfont15);
		
				lblErrormessage = new JLabel("ErrorMessage");
				lblErrormessage.setVisible(false);
				lblErrormessage.setFont(new Font("Monospaced", Font.PLAIN, 10));
				lblErrormessage.setVisible(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(68, Short.MAX_VALUE)
					.addComponent(lblUpdateBlock, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addGap(55))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(80)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(RedValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(GreenValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(BlueValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(87))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(113)
					.addComponent(btnUpdate)
					.addContainerGap(116, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(lblErrormessage, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUpdateBlock)
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(RedValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(GreenValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2)))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_3)
							.addComponent(BlueValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(btnUpdate)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(lblErrormessage, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		layeredPane.setLayout(gl_layeredPane);
		frame.getContentPane().setLayout(groupLayout);
	}
}
