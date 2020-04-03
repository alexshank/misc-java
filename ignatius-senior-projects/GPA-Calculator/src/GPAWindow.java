import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/*
 * Alex Shank
 * Java II
 * 11-12-15
 */
public class GPAWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPercentage1;
	private JTextField txtPercentage2;
	private JTextField txtPercentage3;
	private JTextField txtPercentage4;
	private JTextField txtPercentage5;
	private JTextField txtPercentage6;
	private JTextField txtPercentage7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GPAWindow frame = new GPAWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GPAWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{276, 0, 0, 176, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 34, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitle = new JLabel("SIHS GPA Calculator");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblHappyStatement = new JLabel("Calculate your GPA, underachiever.");
		lblHappyStatement.setFont(new Font("Courier New", Font.PLAIN, 11));
		GridBagConstraints gbc_lblHappyStatement = new GridBagConstraints();
		gbc_lblHappyStatement.insets = new Insets(0, 0, 5, 0);
		gbc_lblHappyStatement.gridx = 3;
		gbc_lblHappyStatement.gridy = 0;
		contentPane.add(lblHappyStatement, gbc_lblHappyStatement);
		
		JLabel lblFirstClass = new JLabel("First Class");
		lblFirstClass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblFirstClass = new GridBagConstraints();
		gbc_lblFirstClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstClass.gridx = 0;
		gbc_lblFirstClass.gridy = 1;
		contentPane.add(lblFirstClass, gbc_lblFirstClass);
		
		JRadioButton rdbtnHonors1 = new JRadioButton("Honors");
		GridBagConstraints gbc_rdbtnHonors1 = new GridBagConstraints();
		gbc_rdbtnHonors1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHonors1.gridx = 1;
		gbc_rdbtnHonors1.gridy = 1;
		contentPane.add(rdbtnHonors1, gbc_rdbtnHonors1);
		
		JRadioButton rdbtnAp1 = new JRadioButton("AP");
		GridBagConstraints gbc_rdbtnAp1 = new GridBagConstraints();
		gbc_rdbtnAp1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAp1.gridx = 2;
		gbc_rdbtnAp1.gridy = 1;
		contentPane.add(rdbtnAp1, gbc_rdbtnAp1);
		
		txtPercentage1 = new JTextField();
		txtPercentage1.setHorizontalAlignment(SwingConstants.CENTER);
		txtPercentage1.setText("Percentage");
		GridBagConstraints gbc_txtPercentage1 = new GridBagConstraints();
		gbc_txtPercentage1.insets = new Insets(0, 0, 5, 0);
		gbc_txtPercentage1.gridx = 3;
		gbc_txtPercentage1.gridy = 1;
		contentPane.add(txtPercentage1, gbc_txtPercentage1);
		txtPercentage1.setColumns(10);
		
		JLabel lblSecondClass = new JLabel("Second Class");
		lblSecondClass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblSecondClass = new GridBagConstraints();
		gbc_lblSecondClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblSecondClass.gridx = 0;
		gbc_lblSecondClass.gridy = 2;
		contentPane.add(lblSecondClass, gbc_lblSecondClass);
		
		JRadioButton rdbtnHonors2 = new JRadioButton("Honors");
		GridBagConstraints gbc_rdbtnHonors2 = new GridBagConstraints();
		gbc_rdbtnHonors2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHonors2.gridx = 1;
		gbc_rdbtnHonors2.gridy = 2;
		contentPane.add(rdbtnHonors2, gbc_rdbtnHonors2);
		
		JRadioButton rdbtnAp2 = new JRadioButton("AP");
		GridBagConstraints gbc_rdbtnAp2 = new GridBagConstraints();
		gbc_rdbtnAp2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAp2.gridx = 2;
		gbc_rdbtnAp2.gridy = 2;
		contentPane.add(rdbtnAp2, gbc_rdbtnAp2);
		
		txtPercentage2 = new JTextField();
		txtPercentage2.setHorizontalAlignment(SwingConstants.CENTER);
		txtPercentage2.setText("Percentage");
		GridBagConstraints gbc_txtPercentage2 = new GridBagConstraints();
		gbc_txtPercentage2.insets = new Insets(0, 0, 5, 0);
		gbc_txtPercentage2.gridx = 3;
		gbc_txtPercentage2.gridy = 2;
		contentPane.add(txtPercentage2, gbc_txtPercentage2);
		txtPercentage2.setColumns(10);
		
		JLabel lblThirdClass = new JLabel("Third Class");
		lblThirdClass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblThirdClass = new GridBagConstraints();
		gbc_lblThirdClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblThirdClass.gridx = 0;
		gbc_lblThirdClass.gridy = 3;
		contentPane.add(lblThirdClass, gbc_lblThirdClass);
		
		JRadioButton rdbtnHonors3 = new JRadioButton("Honors");
		GridBagConstraints gbc_rdbtnHonors3 = new GridBagConstraints();
		gbc_rdbtnHonors3.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHonors3.gridx = 1;
		gbc_rdbtnHonors3.gridy = 3;
		contentPane.add(rdbtnHonors3, gbc_rdbtnHonors3);
		
		JRadioButton rdbtnAp3 = new JRadioButton("AP");
		GridBagConstraints gbc_rdbtnAp3 = new GridBagConstraints();
		gbc_rdbtnAp3.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAp3.gridx = 2;
		gbc_rdbtnAp3.gridy = 3;
		contentPane.add(rdbtnAp3, gbc_rdbtnAp3);
		
		txtPercentage3 = new JTextField();
		txtPercentage3.setHorizontalAlignment(SwingConstants.CENTER);
		txtPercentage3.setText("Percentage");
		GridBagConstraints gbc_txtPercentage3 = new GridBagConstraints();
		gbc_txtPercentage3.insets = new Insets(0, 0, 5, 0);
		gbc_txtPercentage3.gridx = 3;
		gbc_txtPercentage3.gridy = 3;
		contentPane.add(txtPercentage3, gbc_txtPercentage3);
		txtPercentage3.setColumns(10);
		
		JLabel lblFourthClass = new JLabel("Fourth Class");
		lblFourthClass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblFourthClass = new GridBagConstraints();
		gbc_lblFourthClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblFourthClass.gridx = 0;
		gbc_lblFourthClass.gridy = 4;
		contentPane.add(lblFourthClass, gbc_lblFourthClass);
		
		JRadioButton rdbtnHonors4 = new JRadioButton("Honors");
		GridBagConstraints gbc_rdbtnHonors4 = new GridBagConstraints();
		gbc_rdbtnHonors4.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHonors4.gridx = 1;
		gbc_rdbtnHonors4.gridy = 4;
		contentPane.add(rdbtnHonors4, gbc_rdbtnHonors4);
		
		JRadioButton rdbtnAp4 = new JRadioButton("AP");
		GridBagConstraints gbc_rdbtnAp4 = new GridBagConstraints();
		gbc_rdbtnAp4.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAp4.gridx = 2;
		gbc_rdbtnAp4.gridy = 4;
		contentPane.add(rdbtnAp4, gbc_rdbtnAp4);
		
		txtPercentage4 = new JTextField();
		txtPercentage4.setHorizontalAlignment(SwingConstants.CENTER);
		txtPercentage4.setText("Percentage");
		GridBagConstraints gbc_txtPercentage4 = new GridBagConstraints();
		gbc_txtPercentage4.insets = new Insets(0, 0, 5, 0);
		gbc_txtPercentage4.gridx = 3;
		gbc_txtPercentage4.gridy = 4;
		contentPane.add(txtPercentage4, gbc_txtPercentage4);
		txtPercentage4.setColumns(10);
		
		JLabel lblFifthClass = new JLabel("Fifth Class");
		lblFifthClass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblFifthClass = new GridBagConstraints();
		gbc_lblFifthClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblFifthClass.gridx = 0;
		gbc_lblFifthClass.gridy = 5;
		contentPane.add(lblFifthClass, gbc_lblFifthClass);
		
		JRadioButton rdbtnHonors5 = new JRadioButton("Honors");
		GridBagConstraints gbc_rdbtnHonors5 = new GridBagConstraints();
		gbc_rdbtnHonors5.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHonors5.gridx = 1;
		gbc_rdbtnHonors5.gridy = 5;
		contentPane.add(rdbtnHonors5, gbc_rdbtnHonors5);
		
		JRadioButton rdbtnAp5 = new JRadioButton("AP");
		GridBagConstraints gbc_rdbtnAp5 = new GridBagConstraints();
		gbc_rdbtnAp5.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAp5.gridx = 2;
		gbc_rdbtnAp5.gridy = 5;
		contentPane.add(rdbtnAp5, gbc_rdbtnAp5);
		
		txtPercentage5 = new JTextField();
		txtPercentage5.setHorizontalAlignment(SwingConstants.CENTER);
		txtPercentage5.setText("Percentage");
		GridBagConstraints gbc_txtPercentage5 = new GridBagConstraints();
		gbc_txtPercentage5.insets = new Insets(0, 0, 5, 0);
		gbc_txtPercentage5.gridx = 3;
		gbc_txtPercentage5.gridy = 5;
		contentPane.add(txtPercentage5, gbc_txtPercentage5);
		txtPercentage5.setColumns(10);
		
		JLabel lblSixthClass = new JLabel("Sixth Class");
		lblSixthClass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblSixthClass = new GridBagConstraints();
		gbc_lblSixthClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblSixthClass.gridx = 0;
		gbc_lblSixthClass.gridy = 6;
		contentPane.add(lblSixthClass, gbc_lblSixthClass);
		
		JRadioButton rdbtnHonors6 = new JRadioButton("Honors");
		GridBagConstraints gbc_rdbtnHonors6 = new GridBagConstraints();
		gbc_rdbtnHonors6.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHonors6.gridx = 1;
		gbc_rdbtnHonors6.gridy = 6;
		contentPane.add(rdbtnHonors6, gbc_rdbtnHonors6);
		
		JRadioButton rdbtnAp6 = new JRadioButton("AP");
		GridBagConstraints gbc_rdbtnAp6 = new GridBagConstraints();
		gbc_rdbtnAp6.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAp6.gridx = 2;
		gbc_rdbtnAp6.gridy = 6;
		contentPane.add(rdbtnAp6, gbc_rdbtnAp6);
		
		txtPercentage6 = new JTextField();
		txtPercentage6.setHorizontalAlignment(SwingConstants.CENTER);
		txtPercentage6.setText("Percentage");
		GridBagConstraints gbc_txtPercentage6 = new GridBagConstraints();
		gbc_txtPercentage6.insets = new Insets(0, 0, 5, 0);
		gbc_txtPercentage6.gridx = 3;
		gbc_txtPercentage6.gridy = 6;
		contentPane.add(txtPercentage6, gbc_txtPercentage6);
		txtPercentage6.setColumns(10);
		
		JLabel lblSeventhClass = new JLabel("Seventh Class");
		lblSeventhClass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSeventhClass.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblSeventhClass = new GridBagConstraints();
		gbc_lblSeventhClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeventhClass.gridx = 0;
		gbc_lblSeventhClass.gridy = 7;
		contentPane.add(lblSeventhClass, gbc_lblSeventhClass);
		
		JRadioButton rdbtnHonors7 = new JRadioButton("Honors");
		GridBagConstraints gbc_rdbtnHonors7 = new GridBagConstraints();
		gbc_rdbtnHonors7.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHonors7.gridx = 1;
		gbc_rdbtnHonors7.gridy = 7;
		contentPane.add(rdbtnHonors7, gbc_rdbtnHonors7);
		
		JRadioButton rdbtnAp7 = new JRadioButton("AP");
		GridBagConstraints gbc_rdbtnAp7 = new GridBagConstraints();
		gbc_rdbtnAp7.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAp7.gridx = 2;
		gbc_rdbtnAp7.gridy = 7;
		contentPane.add(rdbtnAp7, gbc_rdbtnAp7);
		
		txtPercentage7 = new JTextField();
		txtPercentage7.setHorizontalAlignment(SwingConstants.CENTER);
		txtPercentage7.setText("Percentage");
		GridBagConstraints gbc_txtPercentage7 = new GridBagConstraints();
		gbc_txtPercentage7.insets = new Insets(0, 0, 5, 0);
		gbc_txtPercentage7.gridx = 3;
		gbc_txtPercentage7.gridy = 7;
		contentPane.add(txtPercentage7, gbc_txtPercentage7);
		txtPercentage7.setColumns(10);
		
		JLabel lblOutput = new JLabel("Your grade point average is...");
		lblOutput.setFont(new Font("Courier New", Font.PLAIN, 14));
		GridBagConstraints gbc_lblOutput = new GridBagConstraints();
		gbc_lblOutput.gridx = 3;
		gbc_lblOutput.gridy = 9;
		contentPane.add(lblOutput, gbc_lblOutput);
		
		JButton btnCalculate = new JButton("Calculate!");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)//what happens when you hit "Calculate!"
			{
				try
				{
					String sPercent1 = txtPercentage1.getText();//store each of the percentages
					String sPercent2 = txtPercentage2.getText();//in a variable
					String sPercent3 = txtPercentage3.getText();
					String sPercent4 = txtPercentage4.getText();
					String sPercent5 = txtPercentage5.getText();
					String sPercent6 = txtPercentage6.getText();
					String sPercent7 = txtPercentage7.getText();
									
					int iDifficulty1 = 0;//check 1st class's difficulty
					if(rdbtnHonors1.isSelected())
					{
						iDifficulty1 = 1;
					}
					else if(rdbtnAp1.isSelected())
					{
						iDifficulty1 = 2;
					}
					
					int iDifficulty2 = 0;//check 2nd class's difficulty
					if(rdbtnHonors2.isSelected())
					{
						iDifficulty2 = 1;
					}
					else if(rdbtnAp2.isSelected())
					{
						iDifficulty2 = 2;
					}
					
					int iDifficulty3 = 0;//check 3rd class's difficulty
					if(rdbtnHonors3.isSelected())
					{
						iDifficulty3 = 1;
					}
					else if(rdbtnAp3.isSelected())
					{
						iDifficulty3 = 2;
					}
					
					int iDifficulty4 = 0;//check 4th class's difficulty
					if(rdbtnHonors4.isSelected())
					{
						iDifficulty4 = 1;
					}
					else if(rdbtnAp4.isSelected())
					{
						iDifficulty4 = 2;
					}
					
					int iDifficulty5 = 0;//check 5th class's difficulty
					if(rdbtnHonors5.isSelected())
					{
						iDifficulty5 = 1;
					}
					else if(rdbtnAp5.isSelected())
					{
						iDifficulty5 = 2;
					}
					
					int iDifficulty6 = 0;//check 6th class's difficulty
					if(rdbtnHonors6.isSelected())
					{
						iDifficulty6 = 1;
					}
					else if(rdbtnAp6.isSelected())
					{
						iDifficulty6 = 2;
					}
					
					int iDifficulty7 = 0;//check 7th class's difficulty
					if(rdbtnHonors7.isSelected())
					{
						iDifficulty7 = 1;
					}
					else if(rdbtnAp7.isSelected())
					{
						iDifficulty7 = 2;
					}
					
					//create all the course objects
					Course cOne = new Course(iDifficulty1, sPercent1);
					Course cTwo = new Course(iDifficulty2, sPercent2);
					Course cThree = new Course(iDifficulty3, sPercent3);
					Course cFour = new Course(iDifficulty4, sPercent4);
					Course cFive = new Course(iDifficulty5, sPercent5);
					Course cSix = new Course(iDifficulty6, sPercent6);
					Course cSeven = new Course(iDifficulty7, sPercent7);
					
					double dGPAtotal = 0;
					ArrayList<Course> alCourses = new ArrayList<Course>(); //store objects for looping
					
					alCourses.add(cOne); //add objects to array
					alCourses.add(cTwo);
					alCourses.add(cThree);
					alCourses.add(cFour);
					alCourses.add(cFive);
					alCourses.add(cSix);
					alCourses.add(cSeven);
					
					//add GPA value of each class to a sum
					for(int i = 0; i < alCourses.size(); i++)
					{
						alCourses.get(i).calculateGPA();//sets the course object's GPA
						dGPAtotal += alCourses.get(i).getGPA();
					}
					
					double dFinalGPA = (dGPAtotal / 7);//find the average GPA
					lblOutput.setText("Your GPA: " + Double.toString(dFinalGPA));//print GPA to window
				}
				catch(NumberFormatException e)
				{
					lblOutput.setText("You entered a percentage as a word.");
				}
			}//End of actionPerformed.
			
		});
		btnCalculate.setFont(new Font("Courier New", Font.PLAIN, 18));
		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCalculate.gridx = 0;
		gbc_btnCalculate.gridy = 9;
		contentPane.add(btnCalculate, gbc_btnCalculate);
		
	}

}
