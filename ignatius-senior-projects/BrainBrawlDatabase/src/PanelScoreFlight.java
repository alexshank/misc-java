import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class PanelScoreFlight extends JPanel {
	
	BrawlFrame ParentFrame;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textScore1;
	private JTextField textScore2;

	/**
	 * Create the panel.
	 */
	public PanelScoreFlight(BrawlFrame pParentFrame) {
		setLayout(null);
		
		ParentFrame = pParentFrame;
		
		JRadioButton rdbtnGroupA = new JRadioButton("Group A");
		rdbtnGroupA.setBounds(43, 7, 109, 23);
		add(rdbtnGroupA);
		
		JRadioButton rdbtnGroupB = new JRadioButton("Group B");
		rdbtnGroupB.setBounds(212, 7, 86, 23);
		add(rdbtnGroupB);
		
		JRadioButton rdbtnGroupC = new JRadioButton("Group C");
		rdbtnGroupC.setBounds(406, 7, 109, 23);
		add(rdbtnGroupC);
		
		JRadioButton rdbtnGroupD = new JRadioButton("Group D");
		rdbtnGroupD.setBounds(571, 7, 76, 23);
		add(rdbtnGroupD);
		
		JComboBox TeamList1 = new JComboBox();
		TeamList1.setBounds(10, 82, 170, 20);
		add(TeamList1);
		
		JComboBox TeamList2 = new JComboBox();
		TeamList2.setBounds(520, 82, 170, 20);
		add(TeamList2);
		
		JLabel lblMatch = new JLabel("Match 1/16");
		lblMatch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMatch.setBounds(292, 78, 123, 21);
		add(lblMatch);
		
		textScore1 = new JTextField();
		textScore1.setBounds(160, 171, 86, 20);
		add(textScore1);
		textScore1.setColumns(10);
		
		textScore2 = new JTextField();
		textScore2.setBounds(409, 171, 86, 20);
		add(textScore2);
		textScore2.setColumns(10);
		
		JLabel lblVs = new JLabel("vs.");
		lblVs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVs.setBounds(318, 171, 37, 23);
		add(lblVs);
		
		JLabel lblTeamOne = new JLabel("Team One");
		lblTeamOne.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeamOne.setBounds(26, 174, 109, 17);
		add(lblTeamOne);
		
		JLabel lblTeamTwo = new JLabel("Team Two");
		lblTeamTwo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeamTwo.setBounds(541, 174, 127, 17);
		add(lblTeamTwo);
		
		JButton btnAddScore = new JButton("Add Score!");
		btnAddScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) //what happens when you click "add score!"
			{
				//this needs to take the two int values and add them to a match between the two selected teams
				//this needs to change the button function once all scores for the round are entered.
			}
		});
		btnAddScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddScore.setBounds(245, 228, 170, 49);
		add(btnAddScore);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) //what happens when you hit continue
			{
				//need an increment for each time a round is scored AND THEN EDITED. When the 
				//Increment is at 5
				//the continue button will then take you to the current standings
				//these areas where buttons are called should just call object methods, not contain the code themselves.
				
				PanelReview panelReview = new PanelReview(ParentFrame);
				ParentFrame.setContentPane(panelReview);
				ParentFrame.revalidate();//change panel once all scores are inputed and reviewed
				
			}
		});
		btnContinue.setBounds(599, 416, 91, 23);
		add(btnContinue);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 416, 91, 23);
		add(btnBack);
		
		JLabel lblRound = new JLabel("Round 1");
		lblRound.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRound.setBounds(306, 49, 86, 25);
		add(lblRound);

	}
}
