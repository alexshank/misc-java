/*
 * Alex Shank
 * Java II
 * 11-12-15
 */
public class Course 
{
	private int iDifficulty = 0;
	private double dPercent = 0;
	private double dGPA = 0;
	
	/*
	 * Create a Course object to keep important class information.
	 */
	public Course(int piDifficulty, String pdPercent)
	{
		iDifficulty = piDifficulty;
		dPercent = Double.parseDouble(pdPercent);
		dGPA = 0;
	}//end of constructor
	
	/*
	 * Set the GPA object variable.
	 */
	public void calculateGPA()
	{
		/*
		 * Assign a GPA to the course percentage.
		 */
		if(dPercent >= 97.5)//A+
		{
			dGPA = 4.3;
		}
		else if (dPercent < 97.5 && dPercent >= 94.5)	//A
		{
			dGPA = 4.0;
		}
		else if (dPercent < 94.5 && dPercent >= 92.5)	//A-
		{
			dGPA = 3.7;
		}
		else if (dPercent < 92.5 && dPercent >= 89.5)	//B+
		{
			dGPA = 3.3;
		}
		else if (dPercent < 89.5 && dPercent >= 86.5)	//B
		{
			dGPA = 3.0;
		}
		else if (dPercent < 86.5 && dPercent >= 84.5)	//B-
		{
			dGPA = 2.7;
		}
		else if (dPercent < 84.5 && dPercent >= 81.5)	//C+
		{
			dGPA = 2.3;
		}
		else if (dPercent < 81.5 && dPercent >= 78.5)	//C
		{
			dGPA = 2.0;
		}
		else if (dPercent < 78.5 && dPercent >= 76.5)	//C-
		{
			dGPA = 1.7;
		}
		else if (dPercent < 76.5 && dPercent >= 74.5)	//D+
		{
			dGPA = 1.3;
		}
		else if (dPercent < 74.5 && dPercent >= 71.5)	//D
		{
			dGPA = 1.0;
		}
		else if (dPercent < 71.5 && dPercent >= 69.5)	//D-
		{
			dGPA = 0.7;
		}
		else if (dPercent < 69.5)	//F
		{
			dGPA = 0.0;
		}
		
		/*
		 * Add the weight of the course to the GPA.
		 */
		if(iDifficulty == 1)
		{
			dGPA = (dGPA + .25);
		}
		else if(iDifficulty == 2)
		{
			dGPA = (dGPA + .50);
		}
		
	}//end of calculateGPA
	
	/*
	 * Return the GPA when this method is called.
	 */
	public double getGPA()
	{
		return dGPA;
	}
	
}//end of Course
