import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.omg.CORBA.portable.InputStream;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class PanelMenu extends JPanel 
{
	private static final long serialVersionUID = 1L;
	BrawlFrame ParentFrame;
	
	/**
	 * Create the panel.
	 */
	public PanelMenu(BrawlFrame pParentFrame) 
	{
		
		ParentFrame = pParentFrame;
		
		setLayout(null);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblMainMenu.setBounds(305, 128, 122, 20);
		add(lblMainMenu);
		
		
		JButton btnStartBrawl = new JButton("Start Brain Brawl");
		btnStartBrawl.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)				//when start brain brawl is clicked on
			{				
				PanelAdd panelAdd = new PanelAdd(ParentFrame);
				ParentFrame.setContentPane(panelAdd);
				ParentFrame.revalidate();
			}
		});
		btnStartBrawl.setBounds(289, 174, 149, 23);
		add(btnStartBrawl);
		
		JButton btnLoadGame = new JButton("Load Data");
		btnLoadGame.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)//when load data is clicked on
			{
				//load shit from a file
				JFileChooser fc = new JFileChooser();
				int selection = fc.showOpenDialog(ParentFrame);
				if(selection == JFileChooser.APPROVE_OPTION)
				{
					try 
					{
						Manager m  = Manager.fromFile(fc.getSelectedFile().getAbsolutePath());
						ParentFrame.EventManager = m;
					} 
					catch (ClassNotFoundException e1) 
					{
						e1.printStackTrace();
						System.out.println("Class wasn't found.");
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});										//end of load data
		btnLoadGame.setBounds(289, 220, 149, 23);
		add(btnLoadGame);
		
		JButton btnSaveBrawl = new JButton("Save Data");
		btnSaveBrawl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)//when save data is clicked on
			{
				//save the current shit to a file
				JFileChooser fc = new JFileChooser();
				int result = fc.showSaveDialog(ParentFrame);
				if(result == JFileChooser.APPROVE_OPTION)
				{
					File f = fc.getSelectedFile();
					ParentFrame.EventManager.toFile(f.getAbsolutePath());
				}

			}
		});
		btnSaveBrawl.setBounds(289, 265, 149, 23);
		add(btnSaveBrawl);
		
	}
}
