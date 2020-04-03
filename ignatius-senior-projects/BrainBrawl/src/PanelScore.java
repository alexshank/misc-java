import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
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

import javax.swing.SwingConstants;

import java.awt.Color;
import java.io.File;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class PanelScore extends JPanel {
	
	BrawlFrame ParentFrame;
	JComboBox<String> comboGroup;
	Game gGame = null;
	int iACount = 0;
	int iBCount = 0;
	int iCCount = 0;
	int iDCount = 0;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textScore1;
	private JTextField textScore2;
	int iGameNumber = 1; 
	private JComboBox<String> comboTeam1;
	private JComboBox<String> comboTeam2;
	private JLabel lblCaution;
	int iTeamIndex1;
	int iTeamIndex2;
	int iGroupIndex;
	int iRoundNumber = 0;
	Team tTeamOne;
	Team tTeamTwo;
	int iTeamScore1;
	int iTeamScore2;
	private JButton btnScore;
	private JButton btnContinue;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public PanelScore(BrawlFrame pParentFrame) 
	{
		setLayout(null);
		
		ParentFrame = pParentFrame;
		
		comboTeam1 = new JComboBox<String>();
		comboTeam1.setBounds(10, 82, 170, 20);
		add(comboTeam1);
		
		/*
		 * set group round based on number of games in the group's game arraylist
		 */
		int iGroupSize = ParentFrame.EventManager.getGroup(0).getGamesArray().size();
		if(iGroupSize < 4)
		{
			ParentFrame.EventManager.setRound(1);
		}
		else if(iGroupSize == 4)
		{
			ParentFrame.EventManager.setRound(2);
		}
		else if(iGroupSize == 8)
		{
			ParentFrame.EventManager.setRound(3);
		}
		else if(iGroupSize == 12)
		{
			ParentFrame.EventManager.setRound(4);
		}
		else if(iGroupSize == 16)
		{
			ParentFrame.EventManager.setRound(5);
		}
		else if(iGroupSize == 20)
		{
			ParentFrame.EventManager.setRound(5);
		}
		
		iRoundNumber = ParentFrame.EventManager.getRoundNumber();
		
		/*
		 * set the combobox of group names
		 */
		String[] saGroupNames = new String[4];
		for(int i = 0; i < 4; i++)
		{
			saGroupNames[i] = ParentFrame.EventManager.getGroup(i).getGroupName();
		}
		comboGroup = new JComboBox(saGroupNames);
		comboGroup.setMaximumRowCount(4);
		comboGroup.setBounds(10, 11, 170, 20);
		add(comboGroup);
		comboGroup.setSelectedIndex(0);
		
		comboTeam2 = new JComboBox<String>();
		comboTeam2.setBounds(520, 82, 170, 20);
		add(comboTeam2);
		
		JLabel lblGame = new JLabel("Round 1 - Match 1 of 16");
		lblGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGame.setBounds(197, 11, 493, 21);
		add(lblGame);
		lblGame.setText("Round " + iRoundNumber + " - Match 1 of 16");
		
		textScore1 = new JTextField();
		textScore1.setForeground(Color.LIGHT_GRAY);
		textScore1.setHorizontalAlignment(SwingConstants.CENTER);
		textScore1.setBounds(103, 171, 86, 20);
		add(textScore1);
		textScore1.setColumns(10);
		
		textScore2 = new JTextField();
		textScore2.setForeground(Color.LIGHT_GRAY);
		textScore2.setHorizontalAlignment(SwingConstants.CENTER);
		textScore2.setBounds(475, 171, 86, 20);
		add(textScore2);
		textScore2.setColumns(10);
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVs.setBounds(314, 168, 37, 23);
		add(lblVs);
		
		JLabel lblTeam1 = new JLabel("Team One");
		lblTeam1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeam1.setBounds(20, 113, 247, 42);
		add(lblTeam1);
		
		JLabel lblTeam2 = new JLabel("Team Two");
		lblTeam2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeam2.setBounds(391, 113, 253, 42);
		add(lblTeam2);
		
		btnContinue = new JButton("Continue");					//start this button as not enabled
		btnContinue.setEnabled(false);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) //what happens when you hit continue
			{
				try 
				{
					PanelReview panelReview = new PanelReview(ParentFrame);
					ParentFrame.setContentPane(panelReview);
					ParentFrame.revalidate();//change panel once all scores are inputed and reviewed
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					System.out.println("Issue with the continue function, in PanelScore.");
				}
				
			}
		});
		btnContinue.setBounds(599, 416, 91, 23);
		add(btnContinue);
		
		btnScore = new JButton("Add Score!");
		btnScore.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) //what happens when you click "add score!"
			{
				/*
				 * create game objects from inputed/selected parameters, add to group's game array
				 */
				try
				{	
					/*
					 * check here if group games count is already 4, throw SecurityException for the catch.
					 */
					if(comboGroup.getSelectedIndex() == 0) //increment the group games counter
					{
						if(iACount == 4)
						{
							throw new SecurityException();
						}
					}
					if(comboGroup.getSelectedIndex() == 1)
					{
						if(iBCount == 4)
						{
							throw new SecurityException();
						}
					}
					if(comboGroup.getSelectedIndex() == 2)
					{
						if(iCCount == 4)
						{
							throw new SecurityException();
						}
					}
					if(comboGroup.getSelectedIndex() == 3)
					{
						if(iDCount == 4)
						{
							throw new SecurityException();
						}
					}
					
					lblCaution.setText("");
					iTeamScore1 = Integer.parseInt(textScore1.getText());			//get the scores
					iTeamScore2 = Integer.parseInt(textScore2.getText());
					if(comboTeam1.getSelectedIndex() == comboTeam2.getSelectedIndex())
					{
						throw new ClassCastException();				//team can't play itself
					}
					if(iTeamScore1 == iTeamScore2)
					{
						throw new IllegalArgumentException();				//there can't be a draw
					}
					
					iTeamIndex1 = comboTeam1.getSelectedIndex(); //team number 1
					iTeamIndex2 = comboTeam2.getSelectedIndex(); //team number 2
					iGroupIndex = comboGroup.getSelectedIndex(); //group number
					tTeamOne = ParentFrame.EventManager.getGroup( 				//get first team
							iGroupIndex).getTeam(iTeamIndex1);
					tTeamTwo = ParentFrame.EventManager.getGroup( 				//get second team
							iGroupIndex).getTeam(iTeamIndex2);
					gGame = new Game(tTeamOne, tTeamTwo, iTeamScore1, //create a game with the two teams, put them in group array
							iTeamScore2);
					
					iGameNumber++;
					
					
					iRoundNumber = ParentFrame.EventManager.getRoundNumber();
					lblGame.setText("Round " + iRoundNumber + " - Match " + iGameNumber + " of 16");	//print for user
					
					if (iGameNumber/17 == 1) 
					{
						btnScore.setEnabled(false);
						lblGame.setText("Round " + iRoundNumber + " - All Matches Completed");
					}
					
					ParentFrame.EventManager.getGroup(iGroupIndex).addGame(gGame);	//add game to a group's array
					
					//increment the group game counters
					if(comboGroup.getSelectedIndex() == 0) //increment the group games counter
					{
						iACount++;
					}
					if(comboGroup.getSelectedIndex() == 1)
					{
						iBCount++;
					}
					if(comboGroup.getSelectedIndex() == 2)
					{
						iCCount++;
					}
					if(comboGroup.getSelectedIndex() == 3)
					{
						iDCount++;
					}
					
					if(iACount == 4 && iBCount == 4 && iCCount == 4 && iDCount == 4)
					{
						btnContinue.setEnabled(true);
					}
					
					
				} //end of try
				catch (NumberFormatException e2) 				//catches bad scores
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
				catch(IllegalStateException e5)
				{
					lblCaution.setText("Please select teams that haven't been scored yet.");
				}
				catch(SecurityException e6)
				{
					lblCaution.setText("There are only 4 matches for each group.");
				}
			}//end of button add score
		});
		btnScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnScore.setBounds(245, 228, 170, 49);
		add(btnScore);
		
		/*
		 * Check round number, and if the first round of games were already entered
		 */
		if(ParentFrame.EventManager.wasLoaded() == true && ParentFrame.EventManager.getGroup(0).getGamesArray().size() >= 4)
		{
			iRoundNumber = ParentFrame.EventManager.getRoundNumber();
			
			if(iRoundNumber == 5 && ParentFrame.EventManager.getGroup(0).getGamesArray().size() == 20)		//if every round was scored, let user go to review scores
			{
				lblGame.setText("Round 5 - All Matches Completed");
				
				ParentFrame.EventManager.formFlights();
				
				btnScore.setEnabled(false);
				btnContinue.setEnabled(true);
			}
			else		//all rounds weren't scored, so let the user enter scores
			{
				iRoundNumber = ParentFrame.EventManager.getRoundNumber();
				
				lblGame.setText("Round " + iRoundNumber + " - Match 1 of 16");	//display what round it is for the user
				btnContinue.setEnabled(false);
				btnScore.setEnabled(true);
			}
			
		}
		/*
		 * End of checking if a save was loaded, and if scores should be added this time through the screen
		 */
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() //what happens when you hit the back button
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				PanelAdd panelAdd = new PanelAdd(ParentFrame);
				ParentFrame.setContentPane(panelAdd);
				ParentFrame.revalidate();
			}
		});
		btnBack.setBounds(10, 416, 91, 23);
		add(btnBack);
		
		/*
		 * create combobox models
		 */
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel dcbmA = new DefaultComboBoxModel();
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel dcbmB = new DefaultComboBoxModel();
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel dcbmC = new DefaultComboBoxModel();
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel dcbmD = new DefaultComboBoxModel();
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel dcbmA2 = new DefaultComboBoxModel();
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel dcbmB2 = new DefaultComboBoxModel();
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel dcbmC2 = new DefaultComboBoxModel();
		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel dcbmD2 = new DefaultComboBoxModel();
		for(int i = 0; i < 8; i++)
		{
			dcbmA.addElement(ParentFrame.EventManager.getGroup(0).getTeamNameAt(i));
			dcbmB.addElement(ParentFrame.EventManager.getGroup(1).getTeamNameAt(i));
			dcbmC.addElement(ParentFrame.EventManager.getGroup(2).getTeamNameAt(i));
			dcbmD.addElement(ParentFrame.EventManager.getGroup(3).getTeamNameAt(i));
			dcbmA2.addElement(ParentFrame.EventManager.getGroup(0).getTeamNameAt(i));
			dcbmB2.addElement(ParentFrame.EventManager.getGroup(1).getTeamNameAt(i));
			dcbmC2.addElement(ParentFrame.EventManager.getGroup(2).getTeamNameAt(i));
			dcbmD2.addElement(ParentFrame.EventManager.getGroup(3).getTeamNameAt(i));
		}
		/*
		 * end of create combobox models
		 */
		
		
		/*
		 * make the panel nice for when the user opens it
		 */
		comboTeam1.setModel(dcbmA);
		comboTeam2.setModel(dcbmA2);
		comboTeam1.setSelectedIndex(0);
		comboTeam2.setSelectedIndex(1);
		lblTeam1.setText(comboTeam1.getItemAt(comboTeam1.getSelectedIndex()));
		lblTeam2.setText(comboTeam2.getItemAt(comboTeam2.getSelectedIndex()));
		
		
		lblCaution = new JLabel("");
		lblCaution.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaution.setBounds(183, 290, 280, 14);
		add(lblCaution);
		comboGroup.addActionListener(				//set comboBoxes based on group Combobox
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                    	/*
                		 * load comboboxes based on group
                		 */
                		if(comboGroup.getSelectedIndex() == 0)//first group
                		{
                			comboTeam1.setModel(dcbmA);
                			comboTeam2.setModel(dcbmA2);
                			comboTeam1.setSelectedIndex(0);
                			comboTeam2.setSelectedIndex(1);
                		}
                		if(comboGroup.getSelectedIndex() == 1)//second group
                		{
                			comboTeam1.setModel(dcbmB);
                			comboTeam2.setModel(dcbmB2);
                			comboTeam1.setSelectedIndex(0);
                			comboTeam2.setSelectedIndex(1);
                		}
                		if(comboGroup.getSelectedIndex() == 2)//third group
                		{
                			comboTeam1.setModel(dcbmC);
                			comboTeam2.setModel(dcbmC2);
                			comboTeam1.setSelectedIndex(0);
                			comboTeam2.setSelectedIndex(1);
                		}
                		if(comboGroup.getSelectedIndex() == 3)//fourth group
                		{
                			comboTeam1.setModel(dcbmD);
                			comboTeam2.setModel(dcbmD2);
                			comboTeam1.setSelectedIndex(0);
                			comboTeam2.setSelectedIndex(1);
                		}
                		/*
                		 * end of load comboboxes based on group
                		 */
                		
                		
                    }
                }            
        );
		
		comboTeam1.addActionListener(
                new ActionListener()				//set label to selected team name
                {
                    public void actionPerformed(ActionEvent e)
                    {
                    	int groupIndex = comboGroup.getSelectedIndex();
						int iCombo = comboTeam1.getSelectedIndex();
						String sTeamName1 = ParentFrame.EventManager.getGroup(groupIndex).getTeamNameAt(iCombo);
                		lblTeam1.setText(sTeamName1);
                    }
                }            
        );
		comboTeam2.addActionListener(
                new ActionListener()			//set label to selected team name
                {
                	public void actionPerformed(ActionEvent e)
                    {
                    	int groupIndex = comboGroup.getSelectedIndex();
						int iCombo = comboTeam2.getSelectedIndex();
						String sTeamName1 = ParentFrame.EventManager.getGroup(groupIndex).getTeamNameAt(iCombo);
                		lblTeam2.setText(sTeamName1);
                    }
                }            
        );
	}
}
