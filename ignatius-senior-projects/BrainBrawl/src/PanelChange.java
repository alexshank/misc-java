import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*
 * Alex Shank
 * Java II
 * 12-28-15
 * Edit flight games' scores
 */
public class PanelChange extends JPanel 
{
	BrawlFrame ParentFrame;
	int iFlightIndex;
	String[] sOldGameInfos = new String[4];
	private JLabel lblCaution;
	
	
	/**
	 * Create the panel.
	 */
	public PanelChange(BrawlFrame pParentFrame, int piFlightIndex, String[] psOldGameInfos) 
	{
		ParentFrame = pParentFrame;
		iFlightIndex = piFlightIndex;
		sOldGameInfos = psOldGameInfos;
		
		setLayout(null);
		
		/*
		 * Create String Arrays for the comboboxes with team names
		 */
		ArrayList<String> saGetFlightTeams = new ArrayList<String>();		//get the names of all the teams that are left
		for(int i = 0; i < 8; i++)
		{
			if(ParentFrame.EventManager.getFlightAt(piFlightIndex).getTeamAt(i).getIsAlive() == true)
			{
				saGetFlightTeams.add(ParentFrame.EventManager.getFlightAt(piFlightIndex).getTeamAt(i).getTeamName());
			}
		}
		String[] saFlightTeams = new String[saGetFlightTeams.size()];	//put arraylist in regular array so it can be a combobox model
		for(int i =0; i < saGetFlightTeams.size(); i++)
		{
			saFlightTeams[i] = saGetFlightTeams.get(i);
		}
		
		JComboBox<String> comboTeam1 = new JComboBox(saFlightTeams);
		comboTeam1.setBounds(68, 70, 140, 20);
		add(comboTeam1);
		
		JComboBox<String> comboTeam2 = new JComboBox(saFlightTeams);
		comboTeam2.setBounds(449, 70, 140, 20);
		add(comboTeam2);
		
		JTextField txtScore1 = new JTextField();
		txtScore1.setBounds(145, 173, 86, 20);
		add(txtScore1);
		txtScore1.setColumns(10);
		
		JTextField txtScore2 = new JTextField();
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
					Team tTeam1 = null;
					Team tTeam2 = null;
					String sTeamName1 = comboTeam1.getItemAt(comboTeam1.getSelectedIndex());	//get selected teams' names
					String sTeamName2 = comboTeam2.getItemAt(comboTeam2.getSelectedIndex());
					
					int iTeam1Score = Integer.parseInt(txtScore1.getText()); //get the new scores for the updated game
					int iTeam2Score = Integer.parseInt(txtScore2.getText());
					
					//determine the start of the next loops range based on the flight round number
					int k = 0;
					if(ParentFrame.EventManager.getFlightNumber() == 2)
					{
						k = 4;
					}
					if(ParentFrame.EventManager.getFlightNumber() == 3)
					{
						k = 6;
					}
					//find the team objects based on selected team names
					for(int i = k; i < ParentFrame.EventManager.getFlightAt(iFlightIndex).getGamesArray().size(); i++)
					{
						if(ParentFrame.EventManager.getFlightAt(iFlightIndex).getGameAt(i).getTeam1().getTeamName().equals(sTeamName1))
						{
							tTeam1 = ParentFrame.EventManager.getFlightAt(iFlightIndex).getGameAt(i).getTeam1();
						}
						if(ParentFrame.EventManager.getFlightAt(iFlightIndex).getGameAt(i).getTeam2().getTeamName().equals(sTeamName2))
						{
							tTeam2 = ParentFrame.EventManager.getFlightAt(iFlightIndex).getGameAt(i).getTeam2();
						}
					}
					
					if(comboTeam1.getSelectedIndex() == comboTeam2.getSelectedIndex())		//more checking for user errors
					{
						throw new ClassCastException();				//team can't play itself
					}
					if(iTeam1Score == iTeam2Score)
					{
						throw new IllegalArgumentException();				//there can't be a draw
					}
					
					/*
					 * Find the game's index in the group's arraylist of games by matching teams and scores
					 */
					int iGameIndex = 0;
					String sOldTeamName1 = sOldGameInfos[0];
					int iOldTeamScore1 = Integer.parseInt(sOldGameInfos[1]);
					int iOldTeamScore2 = Integer.parseInt(sOldGameInfos[2]);
					String sOldTeamName2 = sOldGameInfos[3];
					
					//Go through group's names set index to i once match is found
					for(int i = 0; i < ParentFrame.EventManager.getFlightAt(iFlightIndex).getGamesArray().size(); i++)
					{
						String sTempName1 = ParentFrame.EventManager.getFlightAt(iFlightIndex).getGameAt(i).getTeam1().getTeamName();
						String sTempName2 = ParentFrame.EventManager.getFlightAt(iFlightIndex).getGameAt(i).getTeam2().getTeamName();
						int iTempScore1 = ParentFrame.EventManager.getFlightAt(iFlightIndex).getGameAt(i).getTeamScore1();
						int iTempScore2 = ParentFrame.EventManager.getFlightAt(iFlightIndex).getGameAt(i).getTeamScore2();
						if(sTempName1.equals(sOldTeamName1) && sTempName2.equals(sOldTeamName2) && iTempScore1 == iOldTeamScore1 && iTempScore2 == iOldTeamScore2)
						{
							iGameIndex = i;
							System.out.println("A game with the old table info was found and replaced.");
						}
					}
					
					/*
					 * end of getting game index based on old data
					 */
					
					//pass them to method in flight class that will swap out old object values for new values.
					ParentFrame.EventManager.getFlightAt(iFlightIndex).changeGame(tTeam1, tTeam2, iTeam1Score, iTeam2Score, iGameIndex);
					
					panelFix panelfix = new panelFix(ParentFrame);
					ParentFrame.setContentPane(panelfix);				//go back to the review flights screen, should automatically update the table
					ParentFrame.revalidate();								//hopefully...
				}
				catch (NumberFormatException e2) 				//catches bad scores
				{
					lblCaution.setText("You must enter a number for a team's score.");
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
		lblCaution.setBounds(10, 281, 680, 39);
		add(lblCaution);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() 		//when you hit back it cancels the edit game function
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//take you back to the review panel
				panelFix panelfix = new panelFix(ParentFrame);
				ParentFrame.setContentPane(panelfix);
				ParentFrame.revalidate();
			}
		});
		btnBack.setBounds(10, 416, 89, 23);
		add(btnBack);

	}
}


