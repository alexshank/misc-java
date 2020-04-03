import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

/*
 * Alex Shank
 * Java II
 * 12-28-15
 * Print the final winner for each flight
 */
public class PanelWinners extends JPanel 
{
	
	BrawlFrame ParentFrame;
	
	/**
	 * Create the panel.
	 */
	public PanelWinners(BrawlFrame pParentFrame) 
	{
		setLayout(null);
		
		ParentFrame = pParentFrame;

		ParentFrame.EventManager.setWinners();
		for(int i = 0; i < 8; i++)
		{
			System.out.println(ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getWasRanked());
		}
		
		JLabel lblFinalResults = new JLabel("Final Results");
		lblFinalResults.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFinalResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalResults.setBounds(10, 11, 680, 53);
		add(lblFinalResults);
		
		JLabel lblA = new JLabel("Flight A");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setBounds(27, 75, 107, 27);
		add(lblA);
		
		JLabel lblB = new JLabel("Flight B");
		lblB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setBounds(202, 75, 107, 27);
		add(lblB);
		
		JLabel lblC = new JLabel("Flight C");
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setBounds(373, 75, 107, 27);
		add(lblC);
		
		JLabel lblD = new JLabel("Flight D");
		lblD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		lblD.setBounds(537, 75, 107, 27);
		add(lblD);
		
		JLabel lblA1 = new JLabel("A1");
		lblA1.setHorizontalAlignment(SwingConstants.CENTER);
		lblA1.setBounds(27, 130, 107, 27);
		add(lblA1);
		
		JLabel lblA2 = new JLabel("A2");
		lblA2.setHorizontalAlignment(SwingConstants.CENTER);
		lblA2.setBounds(27, 181, 107, 27);
		add(lblA2);
		
		JLabel lblB1 = new JLabel("B1");
		lblB1.setHorizontalAlignment(SwingConstants.CENTER);
		lblB1.setBounds(202, 130, 107, 27);
		add(lblB1);
		
		JLabel lblB2 = new JLabel("B2");
		lblB2.setHorizontalAlignment(SwingConstants.CENTER);
		lblB2.setBounds(202, 181, 107, 27);
		add(lblB2);
		
		JLabel lblC1 = new JLabel("C1");
		lblC1.setHorizontalAlignment(SwingConstants.CENTER);
		lblC1.setBounds(373, 130, 107, 27);
		add(lblC1);
		
		JLabel lblC2 = new JLabel("C2");
		lblC2.setHorizontalAlignment(SwingConstants.CENTER);
		lblC2.setBounds(373, 181, 107, 27);
		add(lblC2);
		
		JLabel lblD1 = new JLabel("D1");
		lblD1.setHorizontalAlignment(SwingConstants.CENTER);
		lblD1.setBounds(537, 130, 107, 27);
		add(lblD1);
		
		JLabel lblD2 = new JLabel("D2");
		lblD2.setHorizontalAlignment(SwingConstants.CENTER);
		lblD2.setBounds(537, 181, 107, 27);
		add(lblD2);
		
		/*
		 * get the winners and runner ups
		 */
		for(int i = 0; i < 8; i++)
		{
			if(ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getIsAlive() == true)
			{
				lblA1.setText(ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getTeamName());
			}
			if(ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getWasRanked() == true)
			{
				lblA2.setText(ParentFrame.EventManager.getFlightAt(0).getTeamAt(i).getTeamName());
			}
		}
		
		for(int i = 0; i < 8; i++)
		{
			if(ParentFrame.EventManager.getFlightAt(1).getTeamAt(i).getIsAlive() == true)
			{
				lblB1.setText(ParentFrame.EventManager.getFlightAt(1).getTeamAt(i).getTeamName());
			}
			if(ParentFrame.EventManager.getFlightAt(1).getTeamAt(i).getWasRanked() == true)
			{
				lblB2.setText(ParentFrame.EventManager.getFlightAt(1).getTeamAt(i).getTeamName());
			}
		}
		
		for(int i = 0; i < 8; i++)
		{
			if(ParentFrame.EventManager.getFlightAt(2).getTeamAt(i).getIsAlive() == true)
			{
				lblC1.setText(ParentFrame.EventManager.getFlightAt(2).getTeamAt(i).getTeamName());
			}
			if(ParentFrame.EventManager.getFlightAt(2).getTeamAt(i).getWasRanked() == true)
			{
				lblC2.setText(ParentFrame.EventManager.getFlightAt(2).getTeamAt(i).getTeamName());
			}
		}
		
		for(int i = 0; i < 8; i++)
		{
			if(ParentFrame.EventManager.getFlightAt(3).getTeamAt(i).getIsAlive() == true)
			{
				lblD1.setText(ParentFrame.EventManager.getFlightAt(3).getTeamAt(i).getTeamName());
			}
			if(ParentFrame.EventManager.getFlightAt(3).getTeamAt(i).getWasRanked() == true)
			{
				lblD2.setText(ParentFrame.EventManager.getFlightAt(3).getTeamAt(i).getTeamName());
			}
		}
		/*
		 * end of get the winners and runners up
		 */
		
	}

}
