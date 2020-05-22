package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGridCell;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PopUpWindowMoveABlock {

	public JFrame frame;
	public int levelindex;
	public LevelDesignPage parent;

	/**
	 * Launch the application.
	 */
	public static void PopUpWindowMoveABlock(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					PopUpWindowMoveABlock window = new PopUpWindowMoveABlock(0);
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
	public PopUpWindowMoveABlock(int levelindex) {
		this.levelindex=levelindex;
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

		Font projectfont18 = projectfont.deriveFont(15f);
		Font projectfont24 = projectfont.deriveFont(24f);
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 289, 289);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane_1, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane_1, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		layeredPane_1.setLayer(panel, 1);
		panel.setBounds(0, 0, 301, 253);
		layeredPane_1.add(panel);
		panel.setBackground(new Color(0,0,51));
		panel.setLayout(null);
		
		JLabel lblMoveABlock = new JLabel("MOVE A BLOCK");
		lblMoveABlock.setForeground(new Color(224, 255, 255));
		lblMoveABlock.setBackground(Color.WHITE);
		lblMoveABlock.setBounds(12, 13, 136, 25);
		lblMoveABlock.setFont(projectfont24);
		panel.add(lblMoveABlock);
		
		SpinnerNumberModel numbermodelH = new SpinnerNumberModel(1, 1, 15,1);
		SpinnerNumberModel numbermodelV = new SpinnerNumberModel(1, 1, 15,1);
		SpinnerNumberModel numbermodel3 = new SpinnerNumberModel(1, 1, 15,1);
		SpinnerNumberModel numbermodel4 = new SpinnerNumberModel(1, 1, 15,1);
		
		JSpinner spinner_1 = new JSpinner(numbermodelV);
		spinner_1.setBounds(221, 64, 30, 22);
		panel.add(spinner_1);
		
		JSpinner spinner = new JSpinner(numbermodelH);
		spinner.setBounds(221, 36, 30, 22);
		panel.add(spinner);
		
		JSpinner spinner_3 = new JSpinner(numbermodel3);
		spinner_3.setBounds(221, 94, 30, 22);
		panel.add(spinner_3);
		
		JSpinner spinner_2 = new JSpinner(numbermodel4);
		spinner_2.setBounds(221, 123, 30, 22);
		panel.add(spinner_2);
		
		JLabel lblOldVerticalGrid = new JLabel("Old Vertical Grid Position:");
		lblOldVerticalGrid.setForeground(new Color(224, 255, 255));
		lblOldVerticalGrid.setBounds(12, 67, 189, 17);
		
		lblOldVerticalGrid.setFont(new Font("DialogInput", Font.PLAIN, 12));
		panel.add(lblOldVerticalGrid);
		
		JLabel lblNewVerticalGrid = new JLabel("New Vertical Grid Position:");
		lblNewVerticalGrid.setForeground(new Color(224, 255, 255));
		lblNewVerticalGrid.setBounds(12, 126, 189, 17);
		lblNewVerticalGrid.setFont(new Font("DialogInput", Font.PLAIN, 12));
		panel.add(lblNewVerticalGrid);
		
		JLabel lblNewHorizontalGrid = new JLabel("New Horizontal Grid Position:");
		lblNewHorizontalGrid.setForeground(new Color(224, 255, 255));
		lblNewHorizontalGrid.setBounds(12, 97, 203, 17);
		lblNewHorizontalGrid.setFont(new Font("DialogInput", Font.PLAIN, 12));
		panel.add(lblNewHorizontalGrid);
		
		
		JLabel lblErrormessage = new JLabel("ErrorMessage");
		lblErrormessage.setForeground(new Color(224, 255, 255));
		lblErrormessage.setFont(new Font("Monospaced", Font.PLAIN, 10));
		lblErrormessage.setBounds(12, 215, 239, 25);
		lblErrormessage.setVisible(false);
		panel.add(lblErrormessage);
		frame.getContentPane().setLayout(groupLayout);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(262, 127, 1, 1);
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1, Short.MAX_VALUE)
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1, Short.MAX_VALUE)
		);
		layeredPane.setLayout(gl_layeredPane);
		panel.add(layeredPane);
		
		JLabel lblOldHorizontalgridPosition = new JLabel("Old Horizontal Grid Position:");
		lblOldHorizontalgridPosition.setForeground(new Color(224, 255, 255));
		lblOldHorizontalgridPosition.setBounds(12, 39, 203, 17);
		lblOldHorizontalgridPosition.setFont(new Font("DialogInput", Font.PLAIN, 12));
		panel.add(lblOldHorizontalgridPosition);
		
		JButton btnNewButton = new JButton("DELETE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			Integer oldxvalue=(Integer) spinner.getValue();
			Integer oldyvalue=(Integer) spinner_1.getValue();
			try {
				Block223Controller.removeBlock(levelindex,oldxvalue, oldyvalue);
				frame.setVisible(false);
//				GameLevelPage page = new GameLevelPage(levelindex);
//				page.frame.setVisible(true);
				
			} catch (InvalidInputException e1) {
				lblErrormessage.setText(e1.getMessage());
				lblErrormessage.setVisible(true);
			}
			
			}
		});
		btnNewButton.setBounds(22, 156, 100, 25);
		btnNewButton.setFont(projectfont18);
		panel.add(btnNewButton);
		
		JButton btnMove = new JButton("MOVE");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			Integer oldHorizontalPos = (Integer) spinner.getValue();
			Integer oldVerticalPos = (Integer) spinner_1.getValue();
			Integer newHorizontalPos = (Integer) spinner_3.getValue();
			Integer newVerticalPos = (Integer) spinner_2.getValue();
			if (oldHorizontalPos==null)
				oldHorizontalPos=0;
			if (oldVerticalPos==null)
				oldVerticalPos=0;
			if (newHorizontalPos==null)
				newHorizontalPos=0;
			if (newVerticalPos==null)
				newVerticalPos=0;
			try {
				Block223Controller.moveBlock(levelindex, oldHorizontalPos, oldVerticalPos, newHorizontalPos, newVerticalPos);
				frame.setVisible(false);
//				GameLevelPage page = new GameLevelPage(levelindex);
//				page.frame.setVisible(true);
				
				
			} catch (InvalidInputException e1) {
				lblErrormessage.setText(e1.getMessage());
				lblErrormessage.setVisible(true);
			}
			
			}
		});
		btnMove.setBounds(134, 156, 81, 25);
		btnMove.setFont(projectfont18);
		panel.add(btnMove);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frame.setVisible(false);
			}
		});
		btnCancel.setFont(projectfont18);
		btnCancel.setBounds(74, 188, 100, 25);
		panel.add(btnCancel);

	}
}
