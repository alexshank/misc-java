import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Font;

/*
 * Alex Shank
 * Java II
 * 12-27-15
 * Fix mistakes with inputting flight scores
 */
public class panelFix extends JPanel 
{
	private JTable tableFix;
	BrawlFrame ParentFrame;

	/**
	 * Create the panel.
	 */
	public panelFix(BrawlFrame pParentFrame) 
	{
		setLayout(null);
		ParentFrame = pParentFrame;
		String[] saFlightGames;
		DefaultTableModel modelA;
		DefaultTableModel modelB;
		DefaultTableModel modelC;
		DefaultTableModel modelD;
		
		/*
		 * Create the 4 group models for when there are 4 games per flight
		 */
		int k = ParentFrame.EventManager.getFlightAt(0).getGamesArray().size();		//make sure the loops don't go out of array bounds
		int j = 0;		//for determining how many games are displayed in the review table
		if(ParentFrame.EventManager.getFlightNumber() == 1)		//based on flight round number, get the right number of games
		{
			j = k - 4;	//will add 4 most recent games to the model.
		}
		else if(ParentFrame.EventManager.getFlightNumber() == 2)
		{
			j = k - 2;	//will add 2 most recent games to the model.
		}
		else if(ParentFrame.EventManager.getFlightNumber() == 3)
		{
			j = k - 1;	//will add most recent game to the model.
		}
		
		modelA = new DefaultTableModel()		//for displaying first flight's games
		{
			private static final long serialVersionUID = 1L;
			@Override	
			public boolean isCellEditable(int row, int column)
			{
				return false;					//Stop the cells from being edited.
			}
		};
		modelA.addColumn("Team One");
		modelA.addColumn("Score One");
		modelA.addColumn("Score Two");
		modelA.addColumn("Team Two");
		for(int i = j; i < k; i++)
		{
			saFlightGames = ParentFrame.EventManager.getFlightAt(0).getGameAt(i).gameToStringArray();
			modelA.addRow(saFlightGames);
		}
		
		modelB = new DefaultTableModel()		//for displaying second flight's games
		{
			private static final long serialVersionUID = 1L;
			@Override	
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		modelB.addColumn("Team One");
		modelB.addColumn("Score One");
		modelB.addColumn("Score Two");
		modelB.addColumn("Team Two");
		for(int i = j; i < k; i++)
		{
			saFlightGames = ParentFrame.EventManager.getFlightAt(1).getGameAt(i).gameToStringArray();
			modelB.addRow(saFlightGames);
		}
		
		modelC = new DefaultTableModel()		//for displaying third flight's games
		{
			private static final long serialVersionUID = 1L;
			@Override	
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		modelC.addColumn("Team One");
		modelC.addColumn("Score One");
		modelC.addColumn("Score Two");
		modelC.addColumn("Team Two");
		for(int i = j; i < k; i++)
		{
			saFlightGames = ParentFrame.EventManager.getFlightAt(2).getGameAt(i).gameToStringArray();
			modelC.addRow(saFlightGames);
		}
		
		modelD = new DefaultTableModel()		//for displaying fourth flight's games
		{
			private static final long serialVersionUID = 1L;
			@Override	
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		modelD.addColumn("Team One");
		modelD.addColumn("Score One");
		modelD.addColumn("Score Two");
		modelD.addColumn("Team Two");
		for(int i = j; i < k; i++)
		{
			saFlightGames = ParentFrame.EventManager.getFlightAt(3).getGameAt(i).gameToStringArray();
			modelD.addRow(saFlightGames);
		}
		/*
		 * end of creating the 4 table models
		 */
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 680, 329);
		add(scrollPane);
		
		tableFix = new JTable();
		tableFix.setBounds(0, 0, 200, 50);
		scrollPane.setViewportView(tableFix);
		tableFix.setVisible(true);
		tableFix.getTableHeader().setReorderingAllowed(false);		//disable reordering columns
		tableFix.getTableHeader().setResizingAllowed(false);			//disable resizing columns
		tableFix.setModel(modelA);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panelKnockout panelknockout = new panelKnockout(ParentFrame);
				ParentFrame.setContentPane(panelknockout);
				ParentFrame.revalidate();
			}
		});
		btnBack.setBounds(10, 416, 91, 23);
		add(btnBack);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 		//what happens when you're done entering flight scores
			{
				if(ParentFrame.EventManager.getFlightNumber() < 3)		//make sure you only increment the flight round if scoring isn't finished
				{
					
					//set is alive to false for the teams that lost
					ParentFrame.EventManager.eliminateTeams();
					
					//Save the data
					JFileChooser fc = new JFileChooser();
					int result = fc.showSaveDialog(ParentFrame);
					if(result == JFileChooser.APPROVE_OPTION)
					{
						File f = fc.getSelectedFile();
						ParentFrame.EventManager.toFile(f.getAbsolutePath());
					}
					
					//go to the next flight games schedule
					ViewFlights viewflights = new ViewFlights(ParentFrame);
					ParentFrame.setContentPane(viewflights);
					ParentFrame.revalidate();
					
				}
				else if(ParentFrame.EventManager.getFlightNumber() >= 3)		//go to winners panel
				{
					
					//call method that will set the winner and runner up in each flight
					ParentFrame.EventManager.setWinners();
					
					//Save the data
					JFileChooser fc = new JFileChooser();
					int result = fc.showSaveDialog(ParentFrame);
					if(result == JFileChooser.APPROVE_OPTION)
					{
						File f = fc.getSelectedFile();
						ParentFrame.EventManager.toFile(f.getAbsolutePath());
					}
					
					PanelWinners panelwinner = new PanelWinners(ParentFrame);
					ParentFrame.setContentPane(panelwinner);
					ParentFrame.revalidate();
					
				}
				
				
				
				
			}
		});
		btnContinue.setBounds(599, 416, 91, 23);
		add(btnContinue);
		
		DefaultComboBoxModel<String> dcbm1 = new DefaultComboBoxModel<String>();
		dcbm1.addElement("Flight A");
		dcbm1.addElement("Flight B");
		dcbm1.addElement("Flight C");
		dcbm1.addElement("Flight D");
		JComboBox<String> comboFlight = new JComboBox<String>(dcbm1);
		comboFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)	//what happens when you select a flight from the combobox
			{
				if(comboFlight.getSelectedIndex() == 0)
				{
					tableFix.setModel(modelA);
				}
				if(comboFlight.getSelectedIndex() == 1)
				{
					tableFix.setModel(modelB);
				}
				if(comboFlight.getSelectedIndex() == 2)
				{
					tableFix.setModel(modelC);
				}
				if(comboFlight.getSelectedIndex() == 3)
				{
					tableFix.setModel(modelD);
				}
			}
		});
		comboFlight.setBackground(Color.PINK);
		comboFlight.setBounds(10, 11, 198, 20);
		add(comboFlight);
		
		JLabel lblFlightRound = new JLabel("Flight Round 1");
		lblFlightRound.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFlightRound.setHorizontalAlignment(SwingConstants.CENTER);
		lblFlightRound.setBounds(218, 11, 472, 32);
		add(lblFlightRound);
		lblFlightRound.setText("Flight Round " + ParentFrame.EventManager.getFlightNumber());
		
		JLabel lblCaution = new JLabel("Edit any scores that are incorrect.");
		lblCaution.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaution.setBounds(212, 416, 377, 23);
		add(lblCaution);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 	//what happens when you hit edit
			{
				try 
				{
					/*
					 * Pass the edit panel the old game info so it can find the old game and change it
					 */
					int iGroupIndex = comboFlight.getSelectedIndex();
					int iRow = tableFix.getSelectedRow();
					String[] oldGameInfo = new String[4];
					oldGameInfo[0] = (String) tableFix.getValueAt(iRow, 0);
					oldGameInfo[1] = (String) tableFix.getValueAt(iRow, 1);
					oldGameInfo[2] = (String) tableFix.getValueAt(iRow, 2);
					oldGameInfo[3] = (String) tableFix.getValueAt(iRow, 3);
					
					
					PanelChange panelchange = new PanelChange(ParentFrame, iGroupIndex, oldGameInfo);
					ParentFrame.setContentPane(panelchange);				//this panel will change the group's game object
					ParentFrame.revalidate();
					
					//the table on this panel should automatically update once it is called back.
				}
				catch (ArrayIndexOutOfBoundsException e1) 			//catch if no row is selected
				{
					lblCaution.setText("You must select the game you wish to edit.");
				}
			}
		});
		btnEdit.setBounds(111, 416, 91, 23);
		add(btnEdit);
		
	}
}
