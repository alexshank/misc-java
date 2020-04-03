import java.io.Serializable;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class Game implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Team tTeam1;
	private int iTeamScore1;
	private Team tTeam2;
	private int iTeamScore2;
	
	public Game(Team ptTeam1, Team ptTeam2, int piTeamScore1, int piTeamScore2)
	{
		tTeam1 = ptTeam1;
		tTeam2 = ptTeam2;
		iTeamScore1 = piTeamScore1;
		iTeamScore2 = piTeamScore2;
	}//end of constructor
	
	public void knockoutTeams()
	{
		if(iTeamScore1 > iTeamScore2)
		{
			tTeam2.eliminate();	//sets isAlive to false
		}
		else if(iTeamScore1 < iTeamScore2)
		{
			tTeam1.eliminate();
		}
	}
	
	public void setWinners()
	{
		if(iTeamScore1 > iTeamScore2)
		{
			tTeam2.makeSecond();
		}
		else if(iTeamScore2 > iTeamScore1)
		{
			tTeam1.makeSecond();
		}
		else
		{
			System.out.println("There was an error setting the final number 1 and number 2 teams.");
		}
	}
	
	public String[] gameToStringArray()
	{
		String[] saGame = new String[4];
		saGame[0] = tTeam1.getTeamName();
		saGame[1] = Integer.toString(iTeamScore1);
		saGame[2] = Integer.toString(iTeamScore2);
		saGame[3] = tTeam2.getTeamName();
		
		return saGame;
	}
	
	public void changeGame(Team ptTeam1, Team ptTeam2, int piTeamScore1, int piTeamScore2)
	{
		tTeam1 = ptTeam1;
		tTeam2 = ptTeam2;
		iTeamScore1 = piTeamScore1;
		iTeamScore2 = piTeamScore2;
	}
	
	public Team getTeam1()
	{
		return tTeam1;
	}
	
	public Team getTeam2()
	{
		return tTeam2;
	}
	
	public int getTeamScore1()
	{
		return iTeamScore1;
	}
	
	public int getTeamScore2()
	{
		return iTeamScore2;
	}
	
	public void addWinOrLoss()
	{
		if(iTeamScore1 > iTeamScore2)
		{
			tTeam1.addWin();
			tTeam2.addLoss();
			tTeam1.addTeamBeaten(tTeam2);
		}
		if(iTeamScore2 > iTeamScore1)
		{
			tTeam2.addWin();
			tTeam1.addLoss();
			tTeam2.addTeamBeaten(tTeam1);
		}
		tTeam1.addPointsFor(iTeamScore1);			//keep track of points won and points lost for later tie-breaking rules
		tTeam1.addPointsAgainst(iTeamScore2);
		tTeam2.addPointsFor(iTeamScore2);
		tTeam2.addPointsAgainst(iTeamScore1);
	}
	
	
	
	
	
}//end of class Match
