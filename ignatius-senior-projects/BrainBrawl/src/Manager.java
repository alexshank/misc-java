import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class Manager implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sEventName = "";		//variables that are swell
	private Group[] agTheGroups;
	private Flight[] afTheFlights;
	private boolean wasLoaded;
	private int iRoundNumber;
	private int iFlightNumber;
	
	
	public Manager(String psEventName)							//the constructor
	{
		sEventName = psEventName;
		agTheGroups = new Group[4];
		afTheFlights = new Flight[4];
		wasLoaded = false;
		iRoundNumber = 0;
		iFlightNumber = 0;
	}
	
	
	/*
	 *set winners 
	 */
	public void setWinners()
	{
		for(int j = 0; j < 4; j++)
		{
			int iLimit = afTheFlights[j].getGamesArray().size();
			afTheFlights[j].getGameAt(iLimit - 1).setWinners();		//call game method that sets wasRanked to true for loser
		}
	}
	
	
	/*
	 * method to knockout teams that lose during flight phase
	 */
	public void eliminateTeams()
	{
		
		if(iFlightNumber < 4)		//for when you're going into the second round
		{
			for(int j = 0; j < 4; j++)
			{
				for(int i = 0; i < afTheFlights[j].getGamesArray().size(); i++)
				{
					afTheFlights[j].getGameAt(i).knockoutTeams();		//call game method that sets isAlive to false for loser
				}
			}
		}
		else
		{
			System.out.println("Error in method eliminateTeams(): The flight round number is all fucked up.");
		}
		
	}
	/*
	 * END of method to knockout teams that lose during flight phase
	 */
	
	
	/*
	 * Method to create the flights and add them to the manager
	 */
	public void formFlights()
	{
		/*
		 * create flight A
		 */
		Team[] teamsA = new Team[8];	//create array of teams, and variables to place in the array
		Team tInputA = null;
		Team tInputB = null;
		Team tInputC = null;
		Team tInputD = null;
		for(int i = 0; i < 8; i++)		//get number one team from each group
		{
			if(agTheGroups[0].getTeam(i).getTeamRank() == 1)
			{
				tInputA = agTheGroups[0].getTeam(i);
			}
			if(agTheGroups[1].getTeam(i).getTeamRank() == 1)
			{
				tInputB = agTheGroups[1].getTeam(i);
			}
			if(agTheGroups[2].getTeam(i).getTeamRank() == 1)
			{
				tInputC = agTheGroups[2].getTeam(i);
			}
			if(agTheGroups[3].getTeam(i).getTeamRank() == 1)
			{
				tInputD = agTheGroups[3].getTeam(i);
			}
		}
		teamsA[0] = tInputA;		//place number one teams in specific team spaces
		teamsA[2] = tInputB;
		teamsA[4] = tInputC;
		teamsA[6] = tInputD;
		for(int i = 0; i < 8; i++)		//get number two team in each group
		{
			if(agTheGroups[0].getTeam(i).getTeamRank() == 2)
			{
				tInputA = agTheGroups[0].getTeam(i);
			}
			if(agTheGroups[1].getTeam(i).getTeamRank() == 2)
			{
				tInputB = agTheGroups[1].getTeam(i);
			}
			if(agTheGroups[2].getTeam(i).getTeamRank() == 2)
			{
				tInputC = agTheGroups[2].getTeam(i);
			}
			if(agTheGroups[3].getTeam(i).getTeamRank() == 2)
			{
				tInputD = agTheGroups[3].getTeam(i);
			}
		}
		teamsA[7] = tInputA;		//place number two teams in specific array spaces
		teamsA[5] = tInputB;
		teamsA[3] = tInputC;
		teamsA[1] = tInputD;
		Flight fFlightA = new Flight("A", teamsA);		//create the flight variable
		/*
		 * end of creating flight A
		 */
		
		/*
		 * create flight B
		 */
		Team[] teamsB = new Team[8];	//create array of teams
		for(int i = 0; i < 8; i++)		//get number one team from each group
		{
			if(agTheGroups[0].getTeam(i).getTeamRank() == 3)
			{
				tInputA = agTheGroups[0].getTeam(i);
			}
			if(agTheGroups[1].getTeam(i).getTeamRank() == 3)
			{
				tInputB = agTheGroups[1].getTeam(i);
			}
			if(agTheGroups[2].getTeam(i).getTeamRank() == 3)
			{
				tInputC = agTheGroups[2].getTeam(i);
			}
			if(agTheGroups[3].getTeam(i).getTeamRank() == 3)
			{
				tInputD = agTheGroups[3].getTeam(i);
			}
		}
		teamsB[0] = tInputA;		//place number one teams in specific team spaces
		teamsB[2] = tInputB;
		teamsB[4] = tInputC;
		teamsB[6] = tInputD;
		for(int i = 0; i < 8; i++)		//get number two team in each group
		{
			if(agTheGroups[0].getTeam(i).getTeamRank() == 4)
			{
				tInputA = agTheGroups[0].getTeam(i);
			}
			if(agTheGroups[1].getTeam(i).getTeamRank() == 4)
			{
				tInputB = agTheGroups[1].getTeam(i);
			}
			if(agTheGroups[2].getTeam(i).getTeamRank() == 4)
			{
				tInputC = agTheGroups[2].getTeam(i);
			}
			if(agTheGroups[3].getTeam(i).getTeamRank() == 4)
			{
				tInputD = agTheGroups[3].getTeam(i);
			}
		}
		teamsB[7] = tInputA;		//place number two teams in specific array spaces
		teamsB[5] = tInputB;
		teamsB[3] = tInputC;
		teamsB[1] = tInputD;
		Flight fFlightB = new Flight("B", teamsB);		//create the flight variable
		/*
		 * end of creating flight B
		 */
		
		/*
		 * create flight C
		 */
		Team[] teamsC = new Team[8];	//create array of teams
		for(int i = 0; i < 8; i++)		//get number one team from each group
		{
			if(agTheGroups[0].getTeam(i).getTeamRank() == 5)
			{
				tInputA = agTheGroups[0].getTeam(i);
			}
			if(agTheGroups[1].getTeam(i).getTeamRank() == 5)
			{
				tInputB = agTheGroups[1].getTeam(i);
			}
			if(agTheGroups[2].getTeam(i).getTeamRank() == 5)
			{
				tInputC = agTheGroups[2].getTeam(i);
			}
			if(agTheGroups[3].getTeam(i).getTeamRank() == 5)
			{
				tInputD = agTheGroups[3].getTeam(i);
			}
		}
		teamsC[0] = tInputA;			//place number one teams in specific team spaces
		teamsC[2] = tInputB;
		teamsC[4] = tInputC;
		teamsC[6] = tInputD;
		for(int i = 0; i < 8; i++)		//get number two team in each group
		{
			if(agTheGroups[0].getTeam(i).getTeamRank() == 6)
			{
				tInputA = agTheGroups[0].getTeam(i);
			}
			if(agTheGroups[1].getTeam(i).getTeamRank() == 6)
			{
				tInputB = agTheGroups[1].getTeam(i);
			}
			if(agTheGroups[2].getTeam(i).getTeamRank() == 6)
			{
				tInputC = agTheGroups[2].getTeam(i);
			}
			if(agTheGroups[3].getTeam(i).getTeamRank() == 6)
			{
				tInputD = agTheGroups[3].getTeam(i);
			}
		}
		teamsC[7] = tInputA;		//place number two teams in specific array spaces
		teamsC[5] = tInputB;
		teamsC[3] = tInputC;
		teamsC[1] = tInputD;
		Flight fFlightC = new Flight("C", teamsC);		//create the flight variable
		/*
		 * end of creating flight C
		 */
		
		/*
		 * create flight D
		 */
		Team[] teamsD = new Team[8];	//create array of teams
		for(int i = 0; i < 8; i++)		//get number one team from each group
		{
			if(agTheGroups[0].getTeam(i).getTeamRank() == 7)
			{
				tInputA = agTheGroups[0].getTeam(i);
			}
			if(agTheGroups[1].getTeam(i).getTeamRank() == 7)
			{
				tInputB = agTheGroups[1].getTeam(i);
			}
			if(agTheGroups[2].getTeam(i).getTeamRank() == 7)
			{
				tInputC = agTheGroups[2].getTeam(i);
			}
			if(agTheGroups[3].getTeam(i).getTeamRank() == 7)
			{
				tInputD = agTheGroups[3].getTeam(i);
			}
		}
		teamsD[0] = tInputA;			//place number one teams in specific team spaces
		teamsD[2] = tInputB;
		teamsD[4] = tInputC;
		teamsD[6] = tInputD;
		for(int i = 0; i < 8; i++)		//get number two team in each group
		{
			if(agTheGroups[0].getTeam(i).getTeamRank() == 8)
			{
				tInputA = agTheGroups[0].getTeam(i);
			}
			if(agTheGroups[1].getTeam(i).getTeamRank() == 8)
			{
				tInputB = agTheGroups[1].getTeam(i);
			}
			if(agTheGroups[2].getTeam(i).getTeamRank() == 8)
			{
				tInputC = agTheGroups[2].getTeam(i);
			}
			if(agTheGroups[3].getTeam(i).getTeamRank() == 8)
			{
				tInputD = agTheGroups[3].getTeam(i);
			}
		}
		teamsD[7] = tInputA;		//place number two teams in specific array spaces
		teamsD[5] = tInputB;
		teamsD[3] = tInputC;
		teamsD[1] = tInputD;
		Flight fFlightD = new Flight("D", teamsD);		//create the flight variable
		/*
		 * end of creating flight D
		 */
		
		afTheFlights[0] = fFlightA;			//add flights to the manager for saving
		afTheFlights[1] = fFlightB;
		afTheFlights[2] = fFlightC;
		afTheFlights[3] = fFlightD;
		
		System.out.println("The flights were formed.");
		
	}
	/*
	 * end of method form flights
	 */
	
	
	public int getFlightNumber()
	{
		return iFlightNumber;
	}
	
	
	public void setFlightRound(int i)
	{
		iFlightNumber = i;
	}
	
	
	public void addFlightRound()
	{
		iFlightNumber++;
	}
	
	
	public Flight getFlightAt(int i)
	{
		return afTheFlights[i];
	}
	
	
	//for saving the data	
	public void toFile(String psFilePath)						//make a save to a file
	{
		try(FileOutputStream fos = new FileOutputStream(psFilePath);
				ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			this.wasLoaded = true;
			oos.writeObject(this);
		}
		catch(Exception e)
		{
			System.out.println("Something is fucky in the save method.");
		}
	}
	
	
	//for loading the data
	public static Manager fromFile(String psFilePath) throws IOException, ClassNotFoundException	//get the save from a file
	{
		try(FileInputStream fis = new FileInputStream(psFilePath);
				ObjectInputStream ois = new ObjectInputStream(fis))
		{
			Manager m = (Manager) ois.readObject();
			m.wasLoaded = true;
			return m;
			
		}
	}
	
	
	/*
	 * This calls the important method that will rank the teams in each group.
	 * They will not be moved around in their arrays, their ranking variable will be changed instead.
	 * I make clones of the group objects here so that the method I call can dispose of teams once they're ranked.
	 */
	public void rankTeams()
	{	
		agTheGroups[0].rankTeams();		//rank group A
		agTheGroups[1].rankTeams();		//rank group B
		agTheGroups[2].rankTeams();		//rank group C
		agTheGroups[3].rankTeams();		//rank group D
	}
	
	
	public void addGroups(Group pgrA, Group pgrB, Group pgrC, Group pgrD)			//add the groups to an array
	{
		agTheGroups[0] = pgrA;
		agTheGroups[1] = pgrB;
		agTheGroups[2] = pgrC;
		agTheGroups[3] = pgrD;
	}
	
	
	public Group getGroup(int i)
	{
		return agTheGroups[i];
	}
	
	
	public Group[] getGroups()
	{
		return agTheGroups;
	}
	
	
	public String getManagerName()
	{
		return sEventName;
	}
	
	
	public Flight[] getFlights()
	{
		return afTheFlights;
	}
	
	
	public boolean wasLoaded()
	{
		return wasLoaded;
	}
	
	
	public void addRound()
	{
		iRoundNumber++;
	}
	
	
	public void setRound(int i)
	{
		iRoundNumber = i;
	}
	
	
	public int getRoundNumber()
	{
		return iRoundNumber;
	}
	
	
}//end of class Manager
