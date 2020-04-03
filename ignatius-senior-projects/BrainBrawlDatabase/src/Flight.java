import java.io.Serializable;
import java.util.ArrayList;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class Flight implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sFlightName = "";
	private Team[] tFlightTeams = new Team[8];
	private ArrayList<Game> alFlightGames;
	
	public Flight(String psFlightName, Team[] ptTeams)
	{
		sFlightName = psFlightName;
		tFlightTeams = ptTeams;
		alFlightGames =  new ArrayList<Game>();
	}//end of constructor
	
	public Team getTeamAt(int i)
	{
		return tFlightTeams[i];
	}
	
	public void addGame(Game pgGame)
	{
		alFlightGames.add(pgGame);
	}
	
	public ArrayList<Game> getGamesArray()
	{
		return alFlightGames;
	}
	
	public Game getGameAt(int i)
	{
		return alFlightGames.get(i);
	}
	public void changeGame(Team ptTeam1, Team ptTeam2, int piScore1, int piScore2, int piGameIndex)
	{
		alFlightGames.get(piGameIndex).changeGame(ptTeam1, ptTeam2, piScore1, piScore2);
	}
	
}//end of class Flight
