import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
//import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class PanelAdd extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BrawlFrame ParentFrame;
	private JTextField textAddToList;
	private JTextField txtGroupCsName;
	private JTextField txtGroupBsName;
	private JTextField txtGroupAsName;
	
	
	
	/*
	 * My variables
	 */
	String sTeamName = "";
	int iTeamCount = 0;
	int iBracketACount = 1;
	int iBracketBCount = 1;
	int iBracketCCount = 1;
	int iBracketDCount = 1;
	ArrayList<Team> atGroupA = new ArrayList<Team>();
	ArrayList<Team> atGroupB = new ArrayList<Team>();
	ArrayList<Team> atGroupC = new ArrayList<Team>();
	ArrayList<Team> atGroupD = new ArrayList<Team>();
	String sGroupAName = "";
	String sGroupBName = "";
	String sGroupCName = "";
	String sGroupDName = "";
	private JTextField txtGroupDsName;
	JButton buttonContinue;
	private JButton btnAddTeam;
	
	
	/**
	 * Create the panel.
	 */
	public PanelAdd(BrawlFrame pParentFrame) 
	{
		
		setLayout(null);
		
		ParentFrame = pParentFrame;
		
		List listGroupA = new List(8);
		listGroupA.setBounds(10, 87, 152, 312);
		add(listGroupA);
		
		List listGroupB = new List(8);
		listGroupB.setBounds(184, 87, 152, 312);
		add(listGroupB);
		
		List listGroupC = new List(8);
		listGroupC.setBounds(356, 87, 152, 312);
		add(listGroupC);
		
		List listGroupD = new List(8);
		listGroupD.setBounds(523, 87, 167, 312);
		add(listGroupD);
		
		txtGroupCsName = new JTextField();
		txtGroupCsName.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroupCsName.setText("Group Name");
		txtGroupCsName.setForeground(Color.LIGHT_GRAY);
		txtGroupCsName.setColumns(10);
		txtGroupCsName.setBounds(381, 51, 132, 20);
		add(txtGroupCsName);
		
		txtGroupBsName = new JTextField();
		txtGroupBsName.setText("Group Name");
		txtGroupBsName.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroupBsName.setForeground(Color.LIGHT_GRAY);
		txtGroupBsName.setColumns(10);
		txtGroupBsName.setBounds(204, 51, 132, 20);
		add(txtGroupBsName);
		
		txtGroupAsName = new JTextField();
		txtGroupAsName.setText("Group Name");
		txtGroupAsName.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroupAsName.setForeground(Color.LIGHT_GRAY);
		txtGroupAsName.setColumns(10);
		txtGroupAsName.setBounds(30, 51, 132, 20);
		add(txtGroupAsName);
		
		txtGroupDsName = new JTextField();
		txtGroupDsName.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroupDsName.setForeground(Color.LIGHT_GRAY);
		txtGroupDsName.setText("Group Name");
		txtGroupDsName.setColumns(10);
		txtGroupDsName.setBounds(547, 51, 143, 20);
		add(txtGroupDsName);
		
		JLabel lblCaution = new JLabel("Assign each team to a group, please.");
		lblCaution.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaution.setBounds(356, 20, 334, 14);
		add(lblCaution);
		
		buttonContinue = new JButton("Continue");
		buttonContinue.setEnabled(false);
		
		String[] acGroupLetters= {"A", "B", "C", "D"};
		@SuppressWarnings("unchecked")
		JComboBox comboGroupLetters = new JComboBox(acGroupLetters);
		
		btnAddTeam = new JButton("Add Team");
		btnAddTeam.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //what happens when you hit Add Team
			{
				addTeam(listGroupA, listGroupB, listGroupC, listGroupD,
						lblCaution, comboGroupLetters);
				
			}
		});
		btnAddTeam.setBounds(498, 409, 91, 23);
		add(btnAddTeam);
		
		int aCount = 1;
		int bCount = 1;
		int cCount = 1;
		int dCount = 1;
		if(ParentFrame.EventManager.wasLoaded() == true) //check if the manager already has data in it.
		{
			
			for(int i = 0; i < 4; i++)		//prints team names for the four groups
			{
				for(String s : ParentFrame.EventManager.getGroup(i).getTeamNames())
				{
					switch(i)									//adds the saved data to the screen, if there is any
					{
						case 0: listGroupA.add(aCount + ". " + s);
								aCount++;
								break;
						case 1: listGroupB.add(bCount + ". " + s);
								bCount++;
								break;
						case 2: listGroupC.add(cCount + ". " + s);
								cCount++;
								break;
						case 3: listGroupD.add(dCount + ". " + s);
								dCount++;
								break;									
					}
				}
			}
			
			buttonContinue.setEnabled(true);	//user can go on to the next screen
			btnAddTeam.setEnabled(false);	//no more teams to be added
			lblCaution.setText("You may proceed to the next screen.");
			
			//put names at top of group
			txtGroupAsName.setText(ParentFrame.EventManager.getGroup(0).getGroupName());
			txtGroupBsName.setText(ParentFrame.EventManager.getGroup(1).getGroupName());
			txtGroupCsName.setText(ParentFrame.EventManager.getGroup(2).getGroupName());
			txtGroupDsName.setText(ParentFrame.EventManager.getGroup(3).getGroupName());
			
		}
		
		
		buttonContinue.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //CONTINUE
			{
				try//this button wont be available until 32 teams have been added or data was loaded
				{
					//this if and else is to see if there is already a manager loaded, or if a new one will be made
					if(ParentFrame.EventManager.wasLoaded() == true)
					{
						
						/*
						 * will take you to the score group screen, hopefully
						 */
						PanelScore panelScore = new PanelScore(ParentFrame);
						ParentFrame.setContentPane(panelScore);
						ParentFrame.revalidate();
						
					}//end of if (going from a save)
					else if (ParentFrame.EventManager.wasLoaded() == false)								//if there is no saved data
					{
						if(txtGroupAsName.getText().equals("") || txtGroupAsName.getText().equals("Group Name")
								|| txtGroupBsName.getText().equals("") || txtGroupBsName.getText().equals("Group Name")
									|| txtGroupCsName.getText().equals("") || txtGroupCsName.getText().equals("Group Name")
										|| txtGroupDsName.getText().equals("") || txtGroupDsName.getText().equals("Group Name"))
						{
							throw new IllegalArgumentException();
						}										//Don't let people forget to add the group names
						
						
						/*
						 * create the group objects
						 */
						sGroupAName = txtGroupAsName.getText();
						sGroupBName = txtGroupBsName.getText();
						sGroupCName = txtGroupCsName.getText();
						sGroupDName = txtGroupDsName.getText();
						Group grA = new Group(sGroupAName, atGroupA);
						Group grB = new Group(sGroupBName, atGroupB);
						Group grC = new Group(sGroupCName, atGroupC);
						Group grD = new Group(sGroupDName, atGroupD);
						
						ParentFrame.EventManager = new Manager("Brain Brawl");
						ParentFrame.EventManager.addGroups(grA, grB, grC, grD);
						
						//save the current shit to a file
						JFileChooser fc = new JFileChooser();
						int result = fc.showSaveDialog(ParentFrame);
						if(result == JFileChooser.APPROVE_OPTION)
						{
							File f = fc.getSelectedFile();
							ParentFrame.EventManager.toFile(f.getAbsolutePath());
						}
						
						/*
						 * will take you to the score group screen, hopefully
						 */
						PanelScore panelscore = new PanelScore(ParentFrame);
						ParentFrame.setContentPane(panelscore);
						ParentFrame.revalidate();
					}//end of else (starting from scratch)
					
					
				}//end of try
				catch(IllegalArgumentException e)					//for when a group isn't named
				{
					e.printStackTrace();
				}
				
				
			}//end of button Continue action listener
		});
		buttonContinue.setBounds(599, 409, 91, 23);
		add(buttonContinue);
		
		
		
		JLabel lblAddStuff = new JLabel("Add Group Names and Teams");
		lblAddStuff.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStuff.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAddStuff.setBounds(10, 11, 326, 29);
		add(lblAddStuff);
		
		
		
		JLabel lblA = new JLabel("A.");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblA.setBounds(10, 47, 25, 29);
		add(lblA);
		
		JLabel lblB = new JLabel("B.");
		lblB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblB.setBounds(180, 51, 25, 20);
		add(lblB);
		
		JLabel lblC = new JLabel("C.");
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblC.setBounds(356, 51, 25, 20);
		add(lblC);
		
		JLabel lblD = new JLabel("D.");
		lblD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblD.setBounds(523, 51, 25, 20);
		add(lblD);
		
		textAddToList = new JTextField();
		textAddToList.setForeground(Color.LIGHT_GRAY);
		textAddToList.setText("Team Name");
		textAddToList.setBounds(111, 410, 311, 20);
		add(textAddToList);
		textAddToList.setColumns(10);
		
		comboGroupLetters.setMaximumRowCount(4);
		comboGroupLetters.setBounds(432, 410, 56, 20);
		add(comboGroupLetters);	
		
		textAddToList.addActionListener(new ActionListener()	//if the enter key is pressed in the text field
		{
		@Override
		public void actionPerformed(ActionEvent e) 
		{		
			addTeam(listGroupA, listGroupB, listGroupC, listGroupD,
					lblCaution, comboGroupLetters);
		
		}
		});
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 		//what happens when you click "back"
			{
				PanelMenu panelmenu = new PanelMenu(ParentFrame);
				ParentFrame.setContentPane(panelmenu);
				ParentFrame.revalidate();
			}
		});
		btnBack.setBounds(10, 409, 91, 23);
		add(btnBack);
		
		
		
	}


	private void addTeam(List listGroupA, List listGroupB, List listGroupC,
			List listGroupD, JLabel lblCaution, JComboBox comboGroupLetters) 
	{
		try
		{
			if(iTeamCount < 32)												//collect all the teams into groups
			{
				if(comboGroupLetters.getSelectedIndex() == 0)	//combo box is set to A
				{
					sTeamName = textAddToList.getText();
					if(sTeamName.equals("") || sTeamName.equals("Team Name")) //make sure team has name
					{
						throw new NullPointerException();
					}
					if(iBracketACount == 9)
					{
						throw new IllegalArgumentException();
					}
					atGroupA.add(new Team(sTeamName, iBracketACount));
					listGroupA.add(iBracketACount + ". " + sTeamName);					//put the team name in its specified column
					textAddToList.setText("");
					iTeamCount++;
					iBracketACount++;
					
				}
				if(comboGroupLetters.getSelectedIndex() == 1)	//combo box is set to B
				{
					sTeamName = textAddToList.getText();
					if(sTeamName.equals("") || sTeamName.equals("Team Name"))
					{
						throw new NullPointerException();
					}
					if(iBracketBCount == 9)
					{
						throw new IllegalArgumentException();
					}
					atGroupB.add(new Team(sTeamName, iBracketBCount));
					listGroupB.add(iBracketBCount + ". " + sTeamName);
					textAddToList.setText("");
					iTeamCount++;
					iBracketBCount++;
					
				}
				if(comboGroupLetters.getSelectedIndex() == 2)	//combo box is set to C
				{
					sTeamName = textAddToList.getText();
					if(sTeamName.equals("") || sTeamName.equals("Team Name"))
					{
						throw new NullPointerException();
					}
					if(iBracketCCount == 9)
					{
						throw new IllegalArgumentException();
					}
					atGroupC.add(new Team(sTeamName, iBracketCCount));
					listGroupC.add(iBracketCCount + ". " + sTeamName);
					textAddToList.setText("");
					iTeamCount++;
					iBracketCCount++;
					
				}
				if(comboGroupLetters.getSelectedIndex() == 3)	//combo box is set to D
				{
					sTeamName = textAddToList.getText();
					if(sTeamName.equals("") || sTeamName.equals("Team Name"))
					{
						throw new NullPointerException();
					}
					if(iBracketDCount == 9)
					{
						throw new IllegalArgumentException();
					}
					atGroupD.add(new Team(sTeamName, iBracketDCount));
					listGroupD.add(iBracketDCount + ". " + sTeamName);
					textAddToList.setText("");
					iTeamCount++;
					iBracketDCount++;
					
				}//end of 4 if statements
				
				lblCaution.setVisible(false);
				
				//if 8th team is entered go to next combobox index
				if(iTeamCount == 8)
				{
					comboGroupLetters.setSelectedIndex(1);
				}
				if(iTeamCount == 16)
				{
					comboGroupLetters.setSelectedIndex(2);
				}
				if(iTeamCount == 24)
				{
					comboGroupLetters.setSelectedIndex(3);
				}
				
				if(iTeamCount == 32)//make continue available
				{
					buttonContinue.setEnabled(true);				//make next page available
				}
				
			}//end of creating groups
			
			
		}//end of try
		catch(IllegalArgumentException e)				//if the user tries to enter a 9th team.
		{
			lblCaution.setVisible(true);
			lblCaution.setText("There are only 8 teams per group.");
		}catch(NullPointerException e)				//if a group name isn't set
		{
			lblCaution.setVisible(true);
			lblCaution.setText("A team's name can't be blank.");
		}
	}//end of method addTeam
	
}
