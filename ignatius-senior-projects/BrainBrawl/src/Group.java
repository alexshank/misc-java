import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**************
 * Alex Shank
 * Java II
 * 11-23-15
 **************/
public class Group implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sGroupName = "";
	private ArrayList<Team> atGroupTeams;
	private ArrayList<Game> agGroupGames;
	
	public Group(String psDivisionName, ArrayList<Team> patTeams)
	{
		sGroupName = psDivisionName;
		atGroupTeams = patTeams;
		agGroupGames = new ArrayList<Game>();
	}//end of constructor
	
	/*
	 * Important method that will view the group's teams and set their rank variable accordingly.
	 * How the teams are ranked:
	 * 1. Record (so wins)
	 * 2. Head-to-head winner
	 * 3. Total points for
	 * 4. Total points against
	 * 5. makes arbitrary decision if this point is reached
	 */
	public void rankTeams()
	{
		try
		{
			ArrayList<Team> Teams = new ArrayList<Team>();
			Teams = atGroupTeams;
			
			int iBestIndex = -1;	//int to keep track of the index that will be ranked at the end of the inner for-loop
			int iMostWins = -1;		//int to keep track of the number of wins the best team has during the inner for-loop
			int iMostPointsFor = -1;	//int to keep track of best index's team's points scored
			int iMostPointsAgainst = -1;	//int to keep track of best index's team's points given up
			int iRankCounter = 1;		//int that is incremented each time the inner for-loop is completed to set the team ranks 1-8
			
			for(int i = 0; i < 8; i++)	//give a team a rank each time this loops, so rank all 8 teams
			{
				for(int k = 0; k < 8; k++)		//go through all 8 teams and check for the best one that's remaining
				{
					if(Teams.get(k).wasRanked() == true)		//check if the team at k was already ranked
					{
						continue;
					}
					else		//see if the team at k is the best remaining team
					{
						
						/*
						 * Check for the most wins
						 */
						if(Teams.get(k).getWins() > iMostWins)	//this team's better than the best
						{
							iBestIndex = k;
							iMostWins = Teams.get(k).getWins();
							iMostPointsFor = Teams.get(k).getPointsFor();
							iMostPointsAgainst = Teams.get(k).getPointsAgainst();
						}
						else if(Teams.get(k).getWins() == iMostWins)	//this team's as good as the best
						{
							
							/*
							 * Check head-to-head
							 */
							if(Teams.get(k).beat(Teams.get(iBestIndex)))	//this team's better than the best
							{
								iBestIndex = k;
								iMostWins = Teams.get(k).getWins();
								iMostPointsFor = Teams.get(k).getPointsFor();
								iMostPointsAgainst = Teams.get(k).getPointsAgainst();
							}
							else if(Teams.get(iBestIndex).beat(Teams.get(k)))	//this team isn't as good as the best
							{
								continue;
							}
							else	//this team is as good as the best
							{
								
								/*
								 * Check for most points
								 */
								if(Teams.get(k).getPointsFor() > iMostPointsFor)	//this team's better than the best
								{
									iBestIndex = k;
									iMostWins = Teams.get(k).getWins();
									iMostPointsFor = Teams.get(k).getPointsFor();
									iMostPointsAgainst = Teams.get(k).getPointsAgainst();
								}
								else if(Teams.get(k).getPointsFor() == iMostPointsFor)
								{
									
									/*
									 * Check points given up
									 */
									if(Teams.get(k).getPointsAgainst() < iMostPointsAgainst)	//this team's better than the best
									{
										iBestIndex = k;
										iMostWins = Teams.get(k).getWins();
										iMostPointsFor = Teams.get(k).getPointsFor();
										iMostPointsAgainst = Teams.get(k).getPointsAgainst();
									}
									else if(Teams.get(k).getPointsAgainst() == iMostPointsAgainst)	//MAKE this team better than the best
									{
										//make an arbitrary decision
										iBestIndex = k;
										iMostWins = Teams.get(k).getWins();
										iMostPointsFor = Teams.get(k).getPointsFor();
										iMostPointsAgainst = Teams.get(k).getPointsAgainst();
									}
									else	//this team is worse than the best
									{
										continue;
									}
									
								}
								else	//this team isn't better than the best
								{
									continue;
								}
								
							}
							
						}
						else	//This team isn't better than the best
						{
							continue;
						}
						
					}
					
				}//end of inner for-loop
				
				//by now, the next best team's index should equal iBestIndex
				Teams.get(iBestIndex).setWasRanked(true);	//stop the recently ranked team from being considered in the next loop
				Teams.get(iBestIndex).setRank(iRankCounter);	//set the team's rank at the best index to the current rank counter value
				iRankCounter++;		//increment the rank counter for the next time through the inner for-loop
				
				//reset best index, and all best index values
				iBestIndex = -1;
				iMostWins = -1;
				iMostPointsFor = -1;
				iMostPointsAgainst = -1;
				
			}//end of outer for-loop
			
			for(int i = 0; i < 8; i++)	//reset all the wasRanked booleans to false
			{
				Teams.get(i).setWasRanked(false);
			}
			
		}//end of try
		catch(Exception e)
		{
			System.out.println("There is a ranking error.");
			e.printStackTrace();
		}
		
	}//end of rankTeams method
	
	public void addTeam(Team tTeam)
	{
		atGroupTeams.add(tTeam);
	}
	
	public void addGame(Game pgGame)
	{
		agGroupGames.add(pgGame);
	}
	
	public Team getTeam(int i)
	{
		return atGroupTeams.get(i);
	}
	
	
	public String getGroupName()
	{
		return sGroupName;
	}
	
	public List<String> getTeamNames()
	{
		ArrayList<String> toReturn = new ArrayList<String>();
		
		for(Team t : atGroupTeams)
		{
			toReturn.add(t.getTeamName());
		}
		
		return toReturn;
	}
	
	public String getTeamNameAt(int i)
	{
		return atGroupTeams.get(i).getTeamName();
	}
	
	public int getTeamRankAt(int i)
	{
		return this.atGroupTeams.get(i).getTeamRank();
	}
	
	public ArrayList<Game> getGamesArray()
	{
		return agGroupGames;
	}
	
	public ArrayList<Team> getTeamsArray()
	{
		return atGroupTeams;
	}
	
	public Game getGameAt(int i)
	{
		return agGroupGames.get(i);
	}
	
	public void changeGame(Team ptTeam1, Team ptTeam2, int piScore1, int piScore2, int piGameIndex)
	{
		agGroupGames.get(piGameIndex).changeGame(ptTeam1, ptTeam2, piScore1, piScore2);
	}
	
	/*
	 * This method is called after the games have been reviewed, and before the teams in each group are ranked.
	 * Hopefully, it will add wins, loses, and defeated opponents to each team.
	 * The important things is that the four most current games are called, because that's how many
	 * are added each round before the teams must be ranked.
	 */
	public void addWinsAndLoses()
	{
		for(int i = 1; i < 5; i++)
		{
			//This should make sure the 4 most recent games are dealt with eventually dealing with all 20 from the 5 rounds
			agGroupGames.get(agGroupGames.size()-i).addWinOrLoss();
		}
	}
	
	
	
	
}//end of class Division
