import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * Alex Shank
 * Java II
 * 12-27-15
 * Panel for adding scores of flight matches
 */
public class panelKnockout extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtScore1;
	private JTextField txtScore2;
	
	BrawlFrame ParentFrame;
	private JLabel lblCaution;
	int iGameLimit = 4;		//variable for setting the number of games each flight plays for the round
	int iRoundGameLimit = 16;
	int iGameNumber = 1;
	int iGamesA = 0;		//variables to keep track of how many games were put in each flight
	int iGamesB = 0;
	int iGamesC = 0;
	int iGamesD = 0;

	/**
	 * Create the panel.
	 */
	public panelKnockout(BrawlFrame pParentFrame) 
	{
		ParentFrame = pParentFrame;
		
		setLayout(null);
		
		JLabel lblVS = new JLabel("VS");
		lblVS.setHorizontalAlignment(SwingConstants.CENTER);
		lblVS.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVS.setBounds(324, 216, 37, 23);
		add(lblVS);
		
		txtScore1 = new JTextField();
		txtScore1.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore1.setForeground(Color.LIGHT_GRAY);
		txtScore1.setColumns(10);
		txtScore1.setBounds(97, 221, 86, 20);
		add(txtScore1);
		
		txtScore2 = new JTextField();
		txtScore2.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore2.setForeground(Color.LIGHT_GRAY);
		txtScore2.setColumns(10);
		txtScore2.setBounds(525, 221, 86, 20);
		add(txtScore2);
		
		JLabel lblTeam1 = new JLabel("Team One");
		lblTeam1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeam1.setBounds(10, 142, 247, 42);
		add(lblTeam1);
		
		JLabel lblTeam2 = new JLabel("Team Two");
		lblTeam2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeam2.setBounds(437, 142, 253, 42);
		add(lblTeam2);
		
		/*
		 * set title based on flight round
		 */
		JLabel lblTitle = new JLabel("Flight Round 1 - Match 1 of 16");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(288, 14, 402, 33);
		add(lblTitle);
		if(ParentFrame.EventManager.getFlightNumber() == 2)
		{
			lblTitle.setText("Flight Round 2 - Match 1 of 8");
			iGameLimit = 2;
			iRoundGameLimit = 8;
		}
		else if(ParentFrame.EventManager.getFlightNumber() == 3)
		{
			lblTitle.setText("Flight Round 3 - Match 1 of 4");
			iGameLimit = 1;
			iRoundGameLimit = 4;
		}
		/*
		 * end of set title based on flight round
		 */
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {		//when you hit back while scoring the flights
			public void actionPerformed(ActionEvent arg0) 
			{
				ViewFlights viewflights = new ViewFlights(ParentFrame);
				ParentFrame.setContentPane(viewflights);
				ParentFrame.revalidate();
			}
		});
		btnBack.setBounds(10, 416, 91, 23);
		add(btnBack);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {		//continue button will take you to review the flight games
			public void actionPerformed(ActionEvent e) 
			{
				panelFix panelfix = new panelFix(ParentFrame);
				ParentFrame.setContentPane(panelfix);
				ParentFrame.revalidate();
			}
		});
		btnContinue.setEnabled(false);
		btnContinue.setBounds(599, 416, 91, 23);
		add(btnContinue);
		
		JComboBox<String> comboTeam1 = new JComboBox<String>();
		comboTeam1.addActionListener(new ActionListener() {		//what happens when you select team one
			public void actionPerformed(ActionEvent e) 
			{
				lblTeam1.setText(comboTeam1.getItemAt(comboTeam1.getSelectedIndex()));
			}
		});
		comboTeam1.setBounds(10, 92, 247, 20);
		add(comboTeam1);
		
		JComboBox<String> comboTeam2 = new JComboBox<String>();
		comboTeam2.addActionListener(new ActionListener() {		//what happens when you select team two
			public void actionPerformed(ActionEvent e) {
				lblTeam2.setText(comboTeam2.getItemAt(comboTeam2.getSelectedIndex()));
			}
		});
		comboTeam2.setBounds(430, 92, 260, 20);
		add(comboTeam2);
		
		/*
		 * Create combobox models for the comboTeam comboboxes
		 */
		DefaultComboBoxModel<String> dcbmA = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> dcbmA2 = new DefaultComboBoxModel<String>();
		for(int i = 0; i < 8; i++)
		{
			if(ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getIsAlive() == true)
			{
				dcbmA.addElement(ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getTeamName());
				dcbmA2.addElement(ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getTeamName());
			}
		}
		DefaultComboBoxModel<String> dcbmB = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> dcbmB2 = new DefaultComboBoxModel<String>();
		for(int i = 0; i < 8; i++)
		{
			if(ParentFrame.EventManager.getFlightAt(1).getTeamAt(i).getIsAlive() == true)
			{
				dcbmB.addElement(ParentFrame.EventManager.getFlightAt(1).getTeamAt(i).getTeamName());
				dcbmB2.addElement(ParentFrame.EventManager.getFlightAt(1).getTeamAt(i).getTeamName());
			}
		}
		DefaultComboBoxModel<String> dcbmC = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> dcbmC2 = new DefaultComboBoxModel<String>();
		for(int i = 0; i < 8; i++)
		{
			if(ParentFrame.EventManager.getFlightAt(2).getTeamAt(i).getIsAlive() == true)
			{
				dcbmC.addElement(ParentFrame.EventManager.getFlightAt(2).getTeamAt(i).getTeamName());
				dcbmC2.addElement(ParentFrame.EventManager.getFlightAt(2).getTeamAt(i).getTeamName());
			}
		}
		DefaultComboBoxModel<String> dcbmD = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> dcbmD2 = new DefaultComboBoxModel<String>();
		for(int i = 0; i < 8; i++)
		{
			if(ParentFrame.EventManager.getFlightAt(3).getTeamAt(i).getIsAlive() == true)
			{
				dcbmD.addElement(ParentFrame.EventManager.getFlightAt(3).getTeamAt(i).getTeamName());
				dcbmD2.addElement(ParentFrame.EventManager.getFlightAt(3).getTeamAt(i).getTeamName());
			}
		}
		/*
		 * end of creating team drop down models
		 */
		
		DefaultComboBoxModel<String> dcbm1 = new DefaultComboBoxModel<String>();
		dcbm1.addElement("Flight A");
		dcbm1.addElement("Flight B");		//create string array for making flight combobox
		dcbm1.addElement("Flight C");
		dcbm1.addElement("Flight D");
		JComboBox<String> comboFlight = new JComboBox<String>(dcbm1);
		comboFlight.addActionListener(new ActionListener() 			//set team dcbm's based on selected flight
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(comboFlight.getSelectedIndex() == 0)
				{
					comboTeam1.setModel(dcbmA);
					comboTeam2.setModel(dcbmA2);
					comboTeam2.setSelectedIndex(1);
				}
				if(comboFlight.getSelectedIndex() == 1)
				{
					comboTeam1.setModel(dcbmB);
					comboTeam2.setModel(dcbmB2);
					comboTeam2.setSelectedIndex(1);
				}
				if(comboFlight.getSelectedIndex() == 2)
				{
					comboTeam1.setModel(dcbmC);
					comboTeam2.setModel(dcbmC2);
					comboTeam2.setSelectedIndex(1);
				}
				if(comboFlight.getSelectedIndex() == 3)
				{
					comboTeam1.setModel(dcbmD);
					comboTeam2.setModel(dcbmD2);
					comboTeam2.setSelectedIndex(1);
				}
			}
		});
		comboFlight.setSelectedIndex(0);
		comboFlight.setBounds(10, 24, 247, 20);
		add(comboFlight);
		
		
		/*
		 * add score button action listener, make game objects for the flight objects in the manager object
		 */
		JButton btnScore = new JButton("Add Score!");		//action listener for the add score button
		btnScore.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{	
					/*
					 * Check if the user did dumb shit and throw an error
					 */
					int iTeamScore1 = Integer.parseInt(txtScore1.getText());			//get the scores for checking illegal arguments
					int iTeamScore2 = Integer.parseInt(txtScore2.getText());
					
					if(comboTeam1.getSelectedIndex() == comboTeam2.getSelectedIndex())
					{
						throw new ClassCastException();
					}
					if(iTeamScore1 == iTeamScore2)
					{
						throw new IllegalArgumentException();
					}
					
					
					if(comboFlight.getSelectedIndex() == 0)		//check if the flight already has all it's games
					{
						if(iGamesA == iGameLimit)
						{
							throw new SecurityException();
						}
						else
						{
							iGamesA++;
						}
					}
					if(comboFlight.getSelectedIndex() == 1)
					{
						if(iGamesB == iGameLimit)
						{
							throw new SecurityException();
						}
						else
						{
							iGamesB++;
						}
					}
					if(comboFlight.getSelectedIndex() == 2)
					{
						if(iGamesC == iGameLimit)
						{
							throw new SecurityException();
						}
						else
						{
							iGamesC++;
						}
					}
					if(comboFlight.getSelectedIndex() == 3)
					{
						if(iGamesD == iGameLimit)
						{
							throw new SecurityException();
						}
						else
						{
							iGamesD++;
						}
					}
					/*
					 * end of checking if the user did dumb shit
					 */
					
					Team tTeam1 = null;		//get teams for making a game object
					Team tTeam2 = null;
					
					String sTeamName1 = comboTeam1.getItemAt(comboTeam1.getSelectedIndex());
					String sTeamName2 = comboTeam2.getItemAt(comboTeam2.getSelectedIndex());
					for(int i =0; i < 8; i++)		//get the teams based on the selected name from the comboboxes
					{
						if(ParentFrame.EventManager.getFlightAt(comboFlight.getSelectedIndex()).getTeamAt(i).getTeamName().equals(sTeamName1))
						{
							tTeam1 = ParentFrame.EventManager.getFlightAt(comboFlight.getSelectedIndex()).getTeamAt(i);
						}
						if(ParentFrame.EventManager.getFlightAt(comboFlight.getSelectedIndex()).getTeamAt(i).getTeamName().equals(sTeamName2))
						{
							tTeam2 = ParentFrame.EventManager.getFlightAt(comboFlight.getSelectedIndex()).getTeamAt(i);
						}
					}
					
					
					//add game to the flight's array of games
					Game gGame = new Game(tTeam1, tTeam2, iTeamScore1, iTeamScore2);					
					ParentFrame.EventManager.getFlightAt(comboFlight.getSelectedIndex()).addGame(gGame);
					
					/*
					 * increment game number, update title, check if all games were entered
					 */
					iGameNumber++;
					int iRoundNumber = ParentFrame.EventManager.getFlightNumber();
					lblTitle.setText("Flight Round " + iRoundNumber + " - Match " + iGameNumber);
					if(iGameNumber == iRoundGameLimit + 1)
					{
						btnScore.setEnabled(false);
						btnContinue.setEnabled(true);
						lblTitle.setText("Flight Round " + iRoundNumber + " - All Matches Completed");
					}
					
					
					
				} //end of try
				catch (NumberFormatException e0) 				//catches bad scores
				{
					lblCaution.setText("You must enter numbers for a team's score.");
				}
				catch (IllegalArgumentException e1)					//catches a draw, which is impossible
				{
					lblCaution.setText("Two Teams can not have a draw.");
				}
				catch(ClassCastException e2)				//catches when the same team is selected twice
				{
					lblCaution.setText("A team can't play itself.");
				}
				catch(SecurityException e3)			//catches when an extra score is added to a flight
				{
					lblCaution.setText("You have already entered all the scores for this flight.");
				}
				
			}
		});
		btnScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnScore.setBounds(261, 278, 170, 49);
		add(btnScore);
		/*
		 * end of button "add score" action listener
		 */
		
		/*
		 * make two teams' names display on open, set comboboxes to separate indexes
		 */
		comboTeam1.setSelectedIndex(0);
		comboTeam2.setSelectedIndex(1);
		lblTeam1.setText(comboTeam1.getItemAt(comboTeam1.getSelectedIndex()));
		lblTeam2.setText(comboTeam2.getItemAt(comboTeam2.getSelectedIndex()));
		
		
		
		lblCaution = new JLabel("");
		lblCaution.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaution.setBounds(10, 350, 680, 33);
		add(lblCaution);
		
//		System.out.println("Number of games in a flight after leaving view flights panel " + ParentFrame.EventManager.getFlightAt(0).getGamesArray().size());
//		System.out.println("Flight number after leaving view flights panel " + ParentFrame.EventManager.getFlightNumber());
//		/*
//		 * if all the games for the round have been added let the user continue
//		 */																						//have to make sure the first round was scored
//		if(ParentFrame.EventManager.getFlightNumber() == 1 && ParentFrame.EventManager.getFlightAt(0).getGamesArray().size() >= 4)
//		{
//			btnContinue.setEnabled(true);
//			btnScore.setEnabled(false);
//			lblTitle.setText("Flight Round 1 - All Matches Complete");
//		}
//		else if(ParentFrame.EventManager.getFlightNumber() == 2)
//		{
//			btnContinue.setEnabled(true);
//			btnScore.setEnabled(false);
//			lblTitle.setText("Flight Round 2 - All Matches Complete");
//		}
//		else if(ParentFrame.EventManager.getFlightNumber() == 3)
//		{
//			btnContinue.setEnabled(true);
//			btnScore.setEnabled(false);
//			lblTitle.setText("Flight Round 3 - All Matches Complete");
//		}
		
				//I don't think I need this check, because you only save if you're done with scoring
				//so you couldn't be in a round and already have scores entered
		

	}
}
