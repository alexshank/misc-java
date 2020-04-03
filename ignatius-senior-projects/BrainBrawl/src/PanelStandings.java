import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.List;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Font;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class PanelStandings extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BrawlFrame ParentFrame;
	DefaultListModel<String> modelA;
	DefaultListModel<String> modelB;
	DefaultListModel<String> modelC;
	DefaultListModel<String> modelD;
	
	/**
	 * Create the panel.
	 */
	public PanelStandings(BrawlFrame pParentFrame) 
	{
		setLayout(null);
		ParentFrame = pParentFrame;
		
		if(ParentFrame.EventManager.getGroup(0).getGamesArray().size() == 20)		//form the flights now so that if the user goes back
		{																			//to previous panel the teams are re-rewarded the points they scored
			//call method that will form the flights								//in the last group round
			ParentFrame.EventManager.formFlights();
		}
		
		JLabel lblTitle = new JLabel("Current Standings");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 680, 35);
		add(lblTitle);
		int iRoundNumber = ParentFrame.EventManager.getRoundNumber();
		lblTitle.setText("Standings After Round " + iRoundNumber);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)		//take you back to the review round scores screen
			{
				PanelReview panelReview = new PanelReview(ParentFrame);
				ParentFrame.setContentPane(panelReview);
				ParentFrame.revalidate();
			}
		});
		btnBack.setBounds(10, 409, 91, 23);
		add(btnBack);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (ParentFrame.EventManager.getRoundNumber() < 5) 		//used when more scores need to be inputted
				{
					//Save the round's scores
					JFileChooser fc = new JFileChooser();
					int result = fc.showSaveDialog(ParentFrame);
					if (result == JFileChooser.APPROVE_OPTION) {
						File f = fc.getSelectedFile();
						ParentFrame.EventManager
								.toFile(f.getAbsolutePath());
					}
					
					PanelScore panelscore = new PanelScore(ParentFrame);	//round is incremented each time this panel is viewed
					ParentFrame.setContentPane(panelscore);
					ParentFrame.revalidate(); //go back to the score round screen, that screen has to realize what round it's on
				}
				//used when data was loaded, but before round 5 was scored
				else if(ParentFrame.EventManager.getRoundNumber() == 5 && ParentFrame.EventManager.getFlightAt(0) == null)
				{
					//Save the round's scores
					JFileChooser fc = new JFileChooser();
					int result = fc.showSaveDialog(ParentFrame);
					if (result == JFileChooser.APPROVE_OPTION) {
						File f = fc.getSelectedFile();
						ParentFrame.EventManager
								.toFile(f.getAbsolutePath());
					}
					
					//call method that will form the flights
					ParentFrame.EventManager.formFlights();
					
					//go to the view flights panel
					ViewFlights viewFlights = new ViewFlights(ParentFrame);
					ParentFrame.setContentPane(viewFlights);
					ParentFrame.revalidate();
					
				}
				else		//used when data was loaded that included scores for round 5
				{
					//Save the round's scores
					JFileChooser fc = new JFileChooser();
					int result = fc.showSaveDialog(ParentFrame);
					if (result == JFileChooser.APPROVE_OPTION) {
						File f = fc.getSelectedFile();
						ParentFrame.EventManager
								.toFile(f.getAbsolutePath());
					}
					
					//call method that will form the flights
					ParentFrame.EventManager.formFlights();
					
					//go to the view flights panel
					ViewFlights viewFlights = new ViewFlights(ParentFrame);
					ParentFrame.setContentPane(viewFlights);
					ParentFrame.revalidate();
					
				}
			}
		});
		btnContinue.setBounds(599, 409, 91, 23);
		add(btnContinue);
		
		/*
		 * Create list models
		 */
		modelA = new DefaultListModel<String>();
		modelB = new DefaultListModel<String>();
		modelC = new DefaultListModel<String>();
		modelD = new DefaultListModel<String>();
		
		List listGroupA = new List(8);
		listGroupA.setBounds(10, 73, 152, 314);
		add(listGroupA);
		
		List listGroupB = new List(8);
		listGroupB.setBounds(187, 73, 152, 314);
		add(listGroupB);
		
		List listGroupC = new List(8);
		listGroupC.setBounds(361, 73, 152, 314);
		add(listGroupC);
		
		List listGroupD = new List(8);
		listGroupD.setBounds(538, 73, 152, 314);
		add(listGroupD);
		
		JLabel lblGroupA = new JLabel("Group A");
		lblGroupA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGroupA.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupA.setBounds(10, 44, 152, 23);
		add(lblGroupA);
		lblGroupA.setText(ParentFrame.EventManager.getGroup(0).getGroupName());
		
		JLabel lblGroupB = new JLabel("Group B");
		lblGroupB.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGroupB.setBounds(187, 44, 152, 23);
		add(lblGroupB);
		lblGroupB.setText(ParentFrame.EventManager.getGroup(1).getGroupName());
		
		JLabel lblGroupC = new JLabel("Group C");
		lblGroupC.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGroupC.setBounds(361, 44, 152, 23);
		add(lblGroupC);
		lblGroupC.setText(ParentFrame.EventManager.getGroup(2).getGroupName());
		
		JLabel lblGroupD = new JLabel("Group D");
		lblGroupD.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGroupD.setBounds(538, 44, 152, 23);
		add(lblGroupD);
		lblGroupD.setText(ParentFrame.EventManager.getGroup(3).getGroupName());
		
		/*
		 * Okay right here we're just going to take all the teams from each group and
		 * print them to JLists in order of rank. Pretty swell if you ask me.
		 */
		for(int k = 0; k < 4; k++)			//represents four groups of teams that must be printed in order of rank
		{
			for(int i = 1; i < 9; i++)		//represents the rank variable
			{
				for(int j = 0; j < 8; j++)	//represents index of the team array in each group
				{
					if(ParentFrame.EventManager.getGroup(k).getTeamRankAt(j) == i)
					{
						String sTeamName = ParentFrame.EventManager.getGroup(k).getTeamNameAt(j);
						if(k == 0)
						{
							listGroupA.add(i + ". " + sTeamName);
						}
						if(k == 1)
						{
							listGroupB.add(i + ". " + sTeamName);
						}
						if(k == 2)
						{
							listGroupC.add(i + ". " + sTeamName);
						}
						if(k == 3)
						{
							listGroupD.add(i + ". " + sTeamName);
						}
					}
					else
					{
						continue;
					}
				}
			}
		}
		//Is this not the coolest triple-for-loop you've ever seen?
		//Are you not entertained?
		
	}
}
