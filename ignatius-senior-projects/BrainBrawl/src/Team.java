import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class Team implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sTeamName = "";
	private int iPointsFor;
	private int iPointsAgainst;
	private int iWins = 0;
	private int iLoses = 0;
	private int iGroupRank = 0;
	private boolean isAlive;
	private ArrayList<Team> alTeamsBeaten;
	private boolean wasRanked;
	
	public Team(String psTeamName, int piBracketRank)
	{
		sTeamName = psTeamName;
		iPointsFor = 0;
		iPointsAgainst = 0;
		iWins = 0;
		iLoses = 0;
		iGroupRank = piBracketRank;
		isAlive = true;
		alTeamsBeaten = new ArrayList<Team>();
		wasRanked = false;
	}//end of constructor
	
	public String getTeamName()
	{
		return sTeamName;
	}
	
	public void makeSecond()
	{
		wasRanked = true;		//can use this variable again because it is set to false after each ranking no matter what
	}							//made a separate method even though I could use the other, it makes the other class simpler to understand
	
	public void eliminate()
	{
		isAlive = false;
	}
	
	/*
	 * This method checks if one team beat another team, by
	 * looking at it's beaten teams array.
	 */
	public boolean beat(Team tInputTeam)
	{
		for(int i = 0; i < alTeamsBeaten.size()-1; i++)
		{
			if(tInputTeam == alTeamsBeaten.get(i))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean wasRanked()
	{
		return wasRanked;
	}
	
	public void setWasRanked(boolean pbBoolean)
	{
		wasRanked = pbBoolean;
	}
	
	public boolean getWasRanked()
	{
		return wasRanked;
	}
	
	public int getTeamRank()
	{
		return iGroupRank;
	}
	
	public void addWin()
	{
		iWins++;
	}
	
	public void addLoss()
	{
		iLoses++;
	}
	
	public void addTeamBeaten(Team ptTeam)
	{
		alTeamsBeaten.add(ptTeam);
	}
	
	public void addPointsFor(int i)
	{
		iPointsFor = iPointsFor + i;
	}
	
	public void addPointsAgainst(int i)
	{
		iPointsAgainst = iPointsAgainst + i;
	}
	
	public int getPointsFor()
	{
		return iPointsFor;
	}
	
	public int getPointsAgainst()
	{
		return iPointsAgainst;
	}
	
	public Team getBeatenTeamAt(int i)
	{
		return alTeamsBeaten.get(i);
	}
	
	public void setRank(int i)
	{
		iGroupRank = i;
	}
	
	public int getWins()
	{
		return iWins;
	}
	
	public int getLoses()
	{
		return iLoses;
	}
	
	public boolean getIsAlive()
	{
		return isAlive;
	}
	
}//end of class Team