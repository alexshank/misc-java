import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/

/*
 *Please ignore the profanities that are contained within the comments of this code.
 *Thank you,
 *			-Alex Shank
 */

public class BrawlFrame extends JFrame 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtUserPassword;
	private JLabel lblWelcome;
	public Manager EventManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					BrawlFrame frame = new BrawlFrame();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		);
	}//end of main

	/**
	 * Create the frame.
	 */
	public BrawlFrame() 
	{
		
		PanelMenu panelmenu = new PanelMenu(this);	//object is used later when switching to a new panel
		EventManager = new Manager("Brain Brawl 2015");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		setResizable(false);//I added this shit, and it works.
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelWelcome = new JPanel();
		contentPane.add(panelWelcome, BorderLayout.CENTER);
		panelWelcome.setLayout(null);
		
		JLabel lblBrainBrawl = new JLabel("Brain Brawl 2015");
		lblBrainBrawl.setBounds(284, 39, 171, 28);
		lblBrainBrawl.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		panelWelcome.add(lblBrainBrawl);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(233, 93, 281, 20);
		txtUserName.setForeground(Color.LIGHT_GRAY);
		txtUserName.setText("User Name");
		panelWelcome.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtUserPassword = new JTextField();
		txtUserPassword.setBounds(233, 130, 281, 20);
		txtUserPassword.setForeground(Color.LIGHT_GRAY);
		txtUserPassword.setText("Password");
		panelWelcome.add(txtUserPassword);
		txtUserPassword.setColumns(10);
		
		/*
		 * For the login panel
		 */
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(328, 162, 98, 23);
		btnEnter.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //what happens when you hit Enter
			{
				String sUserName = "User Name";
				String sUserPassword = "Password";
				try
				{
					if(txtUserName.getText().equals(sUserName) && txtUserPassword.getText().equals(sUserPassword))
					{
						setContentPane(panelmenu);
						revalidate();//you don't need parentframe.revalidate() because this is the parent frame
					}
					else
					{
						throw new IllegalArgumentException();
					}
				}
				catch(IllegalArgumentException e)
				{
					lblWelcome.setText("Your user name or password was incorrect.");
				}
			}
		});
		panelWelcome.add(btnEnter);
		
		lblWelcome = new JLabel("Please enter your user information.");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(275, 231, 208, 14);
		panelWelcome.add(lblWelcome);
		
	}
	
	
	
	
}
