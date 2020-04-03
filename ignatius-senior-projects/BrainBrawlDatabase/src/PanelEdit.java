import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * Alex Shank
 * Java II
 * 12-14-15
 */
public class PanelEdit extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtScore1;
	private JTextField txtScore2;
	private BrawlFrame ParentFrame;
	private String[] oldGameInfo;
	private int iGroupIndex;
	private int iTeam1Score;
	private int iTeam2Score;
	private Team tTeam1;
	private Team tTeam2;
	private JLabel lblCaution;
	private int iGameIndex;

	/**
	 * Create the panel.
	 */
	public PanelEdit(BrawlFrame pParentFrame, int piGroupIndex, String[] pOldGameInfo) 
	{
		ParentFrame = pParentFrame;
		iGroupIndex = piGroupIndex;
		oldGameInfo = pOldGameInfo;
		
		setLayout(null);
		
		/*
		 * Create String Arrays for the comboboxes with team names
		 */
		String[] saGroupTeams = new String[8];
		for(int i = 0; i < 8; i++)
		{
			saGroupTeams[i] = ParentFrame.EventManager.getGroup(piGroupIndex).getTeamNameAt(i);
		}
		
		JComboBox<String> comboTeam1 = new JComboBox(saGroupTeams);
		comboTeam1.setBounds(68, 70, 140, 20);
		add(comboTeam1);
		
		JComboBox<String> comboTeam2 = new JComboBox(saGroupTeams);
		comboTeam2.setBounds(449, 70, 140, 20);
		add(comboTeam2);
		
		txtScore1 = new JTextField();
		txtScore1.setBounds(145, 173, 86, 20);
		add(txtScore1);
		txtScore1.setColumns(10);
		
		txtScore2 = new JTextField();
		txtScore2.setColumns(10);
		txtScore2.setBounds(430, 173, 86, 20);
		add(txtScore2);
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setBounds(306, 176, 46, 14);
		add(lblVs);
		
		JLabel lblEditGameScore = new JLabel("Edit Game Score");
		lblEditGameScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditGameScore.setBounds(252, 11, 171, 14);
		add(lblEditGameScore);
		
		comboTeam1.setSelectedIndex(0);
		comboTeam2.setSelectedIndex(1);
		
		JButton btnChange = new JButton("Change Game");
		btnChange.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) //this action listener will change the game object
			{
				try 
				{
					int iTeamIndex1 = comboTeam1.getSelectedIndex();		//get team index from combobox selections
					int iTeamIndex2 = comboTeam2.getSelectedIndex();
					
					iTeam1Score = Integer.parseInt(txtScore1.getText()); //get the new values for the updated game
					iTeam2Score = Integer.parseInt(txtScore2.getText());
					
					if(comboTeam1.getSelectedIndex() == comboTeam2.getSelectedIndex())	//check for problems with user inputs
					{
						throw new ClassCastException();				//team can't play itself
					}
					if(iTeam1Score == iTeam2Score)
					{
						throw new IllegalArgumentException();				//there can't be a draw
					}
					
					tTeam1 = ParentFrame.EventManager.getGroup(iGroupIndex).getTeam(iTeamIndex1);		//get team objects from user inputs
					tTeam2 = ParentFrame.EventManager.getGroup(iGroupIndex).getTeam(iTeamIndex2);
					
					/*
					 * Find the game's index in the group's arraylist of games by matching old teams and scores
					 */
					iGameIndex = 0;
					String sTeamName1 = oldGameInfo[0];
					int iTeamScore1 = Integer.parseInt(oldGameInfo[1]);
					int iTeamScore2 = Integer.parseInt(oldGameInfo[2]);
					String sTeamName2 = oldGameInfo[3];
					
					//Go through group's games set index to i once match is found
					int j = ParentFrame.EventManager.getGroup(iGroupIndex).getGamesArray().size();
					int k = ParentFrame.EventManager.getGroup(iGroupIndex).getGamesArray().size() - 4;
					for(int i = k; i < j; i++)
					{
						String sTempName1 = ParentFrame.EventManager.getGroup(iGroupIndex).getGameAt(i).getTeam1().getTeamName();
						String sTempName2 = ParentFrame.EventManager.getGroup(iGroupIndex).getGameAt(i).getTeam2().getTeamName();
						int iTempScore1 = ParentFrame.EventManager.getGroup(iGroupIndex).getGameAt(i).getTeamScore1();
						int iTempScore2 = ParentFrame.EventManager.getGroup(iGroupIndex).getGameAt(i).getTeamScore2();
						if(sTempName1.equals(sTeamName1) && sTempName2.equals(sTeamName2) && iTempScore1 == iTeamScore1 && iTempScore2 == iTeamScore2)
						{
							iGameIndex = i;
							System.out.println("old game index was found and replaced with a new game.");
						}
					}
					/*
					 * end of getting game index based on old data
					 */
					
					//pass new user inputs to method in group class that will swap out old object values for new values.
					ParentFrame.EventManager.getGroup(iGroupIndex).changeGame(tTeam1, tTeam2, iTeam1Score, iTeam2Score, iGameIndex);
					
					PanelReview panelreview = new PanelReview(ParentFrame);
					ParentFrame.setContentPane(panelreview);				//go back to the review screen, should automatically update the table
					ParentFrame.revalidate();
				}
				catch (NumberFormatException e2) 				//catches bad scores (letters not numbers)
				{
					lblCaution.setText("You can only enter numbers for a team's score.");
				}
				catch (IllegalArgumentException e3)					//catches a draw, which is impossible
				{
					lblCaution.setText("Two Teams can not have a draw.");
				}
				catch(ClassCastException e4)				//catches when the same team is selected twice
				{
					lblCaution.setText("A team can't play itself.");
				}
				
			}
		});
		btnChange.setBounds(275, 237, 120, 23);
		add(btnChange);
		
		lblCaution = new JLabel("Please enter a match to replace the old one with.");
		lblCaution.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaution.setBounds(10, 281, 680, 30);
		add(lblCaution);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() 		//when you hit back it cancels the edit game function
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//take you back to the review panel
				PanelReview panelreview = new PanelReview(ParentFrame);
				ParentFrame.setContentPane(panelreview);
				ParentFrame.revalidate();
			}
		});
		btnBack.setBounds(10, 416, 89, 23);
		add(btnBack);

	}
}
