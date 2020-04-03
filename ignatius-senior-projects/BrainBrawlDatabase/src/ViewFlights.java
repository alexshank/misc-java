import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class ViewFlights extends JPanel {

	BrawlFrame ParentFrame;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ViewFlights(BrawlFrame pParentFrame) 
	{
		ParentFrame = pParentFrame;
		
		setLayout(null);
		
		/*
		 * set flight round based on number of games in the flight's game arraylist
		 */
		int iTeamsLeft = 0;
		for(int i = 0; i < 8; i++)
		{
			if(ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getIsAlive() == true)
			{
				iTeamsLeft++;
			}
		}
		
		if(iTeamsLeft == 8)
		{
			ParentFrame.EventManager.setFlightRound(1);
		}
		else if(iTeamsLeft == 4)
		{
			ParentFrame.EventManager.setFlightRound(2);
		}
		else if(iTeamsLeft == 2)
		{
			ParentFrame.EventManager.setFlightRound(3);
		}
		/*
		 * end of setting round on screen start
		 */
		
		JLabel lblMatches = new JLabel("Matches");
		lblMatches.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatches.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMatches.setBounds(10, 40, 125, 22);
		add(lblMatches);
		
		JLabel lblGroupA = new JLabel("Flight A");
		lblGroupA.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGroupA.setBounds(148, 40, 125, 22);
		add(lblGroupA);
		
		JLabel lblGroupB = new JLabel("Flight B");
		lblGroupB.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGroupB.setBounds(283, 40, 125, 22);
		add(lblGroupB);
		
		JLabel lblGroupC = new JLabel("Flight C");
		lblGroupC.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGroupC.setBounds(422, 40, 125, 22);
		add(lblGroupC);
		
		JLabel lblGroupD = new JLabel("Flight D");
		lblGroupD.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGroupD.setBounds(565, 40, 125, 22);
		add(lblGroupD);
		
		DefaultListModel<String> modelMatches = new DefaultListModel<String>();
		JList<String> listMatches = new JList<String>(modelMatches);
		listMatches.setBounds(10, 73, 125, 332);
		add(listMatches);
		
		/*
		 * Set match instructions based on round
		 */
		int iFlightNumber = ParentFrame.EventManager.getFlightNumber();
		if(iFlightNumber == 1)
		{
			modelMatches.removeAllElements();
			modelMatches.addElement("1 vs. 2");
			modelMatches.addElement("3 vs. 4");
			modelMatches.addElement("5 vs. 6");
			modelMatches.addElement("7 vs. 8");
		}
		else if(iFlightNumber == 2)
		{
			modelMatches.removeAllElements();
			modelMatches.addElement("1 vs. 2");
			modelMatches.addElement("3 vs. 4");
		}
		else
		{
			modelMatches.removeAllElements();
			modelMatches.addElement("1 vs. 2");
		}
		/*
		 * end of setting match instructions
		 */
		
		/*
		 * Create the four DefaultListModels for adding elements to
		 */
		DefaultListModel<String> modelA = new DefaultListModel<String>();
		DefaultListModel<String> modelB = new DefaultListModel<String>();
		DefaultListModel<String> modelC = new DefaultListModel<String>();
		DefaultListModel<String> modelD = new DefaultListModel<String>();
		
		JList<String> listA = new JList<String>(modelA);
		listA.setBounds(148, 73, 125, 332);
		add(listA);
		
		JList<String> listB = new JList<String>(modelB);
		listB.setBounds(283, 73, 125, 332);
		add(listB);
		
		JList<String> listC = new JList<String>(modelC);
		listC.setBounds(422, 73, 125, 332);
		add(listC);
		
		JList<String> listD = new JList<String>(modelD);
		listD.setBounds(565, 73, 125, 332);
		add(listD);
		
		/*
		 * Print teams to flight lists so that the user knows who plays who
		 */
		int aCount = 1;
		int bCount = 1;
		int cCount = 1;
		int dCount = 1;
		for(int i = 0; i < 8; i++)		//go through 8 team index and print those that are still in
		{
			if(ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getIsAlive() == true)
			{
				modelA.addElement(aCount + ". " + ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getTeamName());
				aCount++;
			}
			if(ParentFrame.EventManager.getFlightAt(1).getTeamAt(i).getIsAlive() == true)
			{
				modelB.addElement(bCount + ". " + ParentFrame.EventManager.getFlightAt(1).getTeamAt(i).getTeamName());
				bCount++;
			}
			if(ParentFrame.EventManager.getFlightAt(2).getTeamAt(i).getIsAlive() == true)
			{
				modelC.addElement(cCount + ". " + ParentFrame.EventManager.getFlightAt(2).getTeamAt(i).getTeamName());
				cCount++;
			}
			if(ParentFrame.EventManager.getFlightAt(3).getTeamAt(i).getIsAlive() == true)
			{
				modelD.addElement(dCount + ". " + ParentFrame.EventManager.getFlightAt(3).getTeamAt(i).getTeamName());
				dCount++;
			}
		}
		/*
		 * End of printing teams to flight lists (if they're still alive)
		 */
		
		iFlightNumber = ParentFrame.EventManager.getFlightNumber();
		JLabel lblViewFlightMatches = new JLabel("View Flight Matches - Round " + iFlightNumber);
		lblViewFlightMatches.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewFlightMatches.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblViewFlightMatches.setBounds(10, 11, 680, 22);
		add(lblViewFlightMatches);
		
		JButton btnContinue = new JButton("Continue");		//what happens when you hit "continue"
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//go to add scores screen
				panelKnockout panelknockout = new panelKnockout(ParentFrame);
				ParentFrame.setContentPane(panelknockout);
				ParentFrame.revalidate();
				
			}
		});
		btnContinue.setBounds(599, 416, 91, 23);
		add(btnContinue);
		
		JButton btnBack = new JButton("Back");		//what happens when you hit "back"
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				/*
				 * go back to either the review group round screen, or review flight round screen
				 */
				
				int iFlightGames = ParentFrame.EventManager.getFlightAt(0).getGamesArray().size();
				
				if(iFlightGames < 4)
				{
					//go back to group standings screen
					PanelStandings panelstandings = new PanelStandings(ParentFrame);
					ParentFrame.setContentPane(panelstandings);
					ParentFrame.revalidate();
				}
				else
				{
					//go back to review flights screen
					panelFix panelfix = new panelFix(ParentFrame);
					ParentFrame.setContentPane(panelfix);
					ParentFrame.revalidate();
				}
				
			}
		});
		btnBack.setBounds(10, 416, 91, 23);
		add(btnBack);

	}
}
