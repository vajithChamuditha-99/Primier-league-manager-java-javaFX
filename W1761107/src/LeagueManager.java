import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Date;

public interface LeagueManager {

    void addSportsClub(FootballClub footballClub);
    void deleteSportsClub(String name,String clubLocation);
    void printSportsClubList();
    void addMatchDetails();
    void printStatistics(String name);
    void displayLeagueTable();
    void saveDataToFile();
    void loadDataFromFile() throws IOException;
    void randomMatchPicker() throws IOException;
    ObservableList<FootballClub> points();
    ObservableList<FootballClub> goalsScored();
    ObservableList<FootballClub> largestWins();
    ObservableList<Match> accordingToDate();
    ObservableList<Match> matchDate(Date date);
    ObservableList<Match> matchesPlayed();
}