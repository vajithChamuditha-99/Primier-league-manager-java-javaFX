
import com.sun.org.apache.xpath.internal.operations.Equals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PremierLeagueManager implements LeagueManager{
    //creating the global array lists
    private final ArrayList<FootballClub> league= new ArrayList<>();
    private final ArrayList<Match> matches= new ArrayList<>();

    public PremierLeagueManager() {
    }

    @Override
    //add footballclub
    public void addSportsClub(FootballClub footballClub) {
        //checking the football club already in the league
        if (league.contains(footballClub)){
            System.out.println("The club is already in the league!!!...");
        }else {
            //adding the football club into the league
            league.add(footballClub);
            System.out.println(footballClub.getName() + " Successfully Added to the League");
        }

    }

    @Override
    public void deleteSportsClub(String name,String clubLocation) {
        //checking the football club list is empty
        if (league.isEmpty()){
            System.out.println("No Clubs in the League!!!...");
            return;
        }

        boolean checker = false;
        //finding the relevent club throughout the league list
        for (FootballClub footballClub : league){
            //checking the name , location correct
            if (footballClub.getName().equalsIgnoreCase(name)){
                if (footballClub.getLocation().equalsIgnoreCase(clubLocation)){
                    //removing the relevent club
                    league.remove(footballClub);
                    System.out.println("Successfully Deleted " + name);
                    checker=true;
                    break;
                }
            }
        }
        //if no matching results found
        if (!checker){
            System.out.println("Entered details does not match!!!...");
        }
    }

    @Override
    public void printSportsClubList() {
        //checking list is empty
        if (league.isEmpty()){
            System.out.println("No Clubs in the League!!!...");
            return;
        }
        //printing all the football clubs using for loop
        for (FootballClub footballClub: league){
            System.out.println(footballClub);
        }
    }

    @Override
    public void printStatistics(String name) {
        //checking list is empty
        if (league.isEmpty()){
            System.out.println("No clubs to show Statistics!!!...");
            return;
        }

        boolean checker=false;
        //finding the relevent football club throughout the list
        for (FootballClub footballClub : league){
            //checking details matching or not
            if (footballClub.getName().equals(name)){
                //printing the statistics
                System.out.println("Club name:                "
                        + footballClub.getName());
                System.out.println("Matches Played:           "
                        + footballClub.getNumberOfMatchesPlayed());
                System.out.println("Current number of Points: "
                        + footballClub.getCurrentNumberOfPoints());
                System.out.println("Matches Won:              "
                        + footballClub.getNumberOfWins());
                System.out.println("Matches Lost:             "
                        + footballClub.getNumberOfLoss());
                System.out.println("Matches Draw:             "
                        + footballClub.getNumberOfDraws());
                System.out.println("Goals Scored:             "
                        + footballClub.getNumberOfGoalsScored());
                System.out.println("Goals Received:           "
                        + footballClub.getNumberOfGoalsRecieved());
                checker=true;
                break;
            }
        }
        //if no matching results found
        if (!checker){
            System.out.println("No Matching Clubs Found!!!...");
        }
    }

    @Override
    public void displayLeagueTable() {
        if (league.isEmpty()){
            System.out.println("No clubs to show Statistics!!!...");
            return;
        }
        //setting the league list descending order
        league.sort(Collections.reverseOrder());
        for (FootballClub footballClub: league){
            //printing the league table
            System.out.println(footballClub.getName()+ "|| Matches played: " +footballClub.getNumberOfMatchesPlayed()+
                    "|| Matches won: " + footballClub.getNumberOfWins() + "|| Matches lost: "+footballClub.getNumberOfLoss()+
                    "|| Matches draw: "+footballClub.getNumberOfDraws()+"|| Points got: "+footballClub.getCurrentNumberOfPoints() +
                    "|| Total goals scored: "+footballClub.getNumberOfGoalsScored()+"|| Total goals received: "+footballClub.getNumberOfGoalsRecieved());
        }
    }


    @Override
    public void addMatchDetails() {
        Scanner scanner=new Scanner(System.in);
        //checking the list is empty
        if(league.isEmpty()){
            System.out.println("No Clubs to Add Match Details!!!...");
            return;
        }
        //getting the match date from the user
        System.out.println("Enter Date (yyyy-mm-dd): ");
        String day = scanner.next();
        Date date;
        try {
            //setting the date n Date format
            date = new SimpleDateFormat("yyyy-MM-dd").parse(day);

        } catch (ParseException e) {
            System.out.println("You have to enter date in format yyyy-MM-dd");
            return;
        }

        /*
        int month1= date.getMonth()+1;
            if (date.getDate()>31 || date.getDate()<1 || month1>12||(month1)<1){
                System.out.println("Please enter a valid Date !!!");
                return;
            }
        System.out.println(date.getMonth()+1);
        System.out.println(date.getDate());
         */
        System.out.println("Enter the team 1 :");
        String teamOne=scanner.next();

        boolean checkerOne= Boolean.FALSE;
        for (FootballClub footballClub : league) {
            //checking entered club is containing in the list
            if (footballClub.getName().equalsIgnoreCase(teamOne)) {
                //setting  number of matches plus one
                footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed()+1);
                //match.setClubOne(teamOne);
                //System.out.println(match.getClubOne());
                checkerOne=true;

            }
        }
        //football club not found
        if (!checkerOne){
            System.out.println("No matching clubs to "+ teamOne+ " in the league!!!...");
            return;
        }

        System.out.println("Enter the team 2 :");
        String teamTwo=scanner.next();


        /*
        for (FootballClub footballClub : league){
            if (footballClub.getName().equalsIgnoreCase(teamOne)){
                return;
            }
        }*/

        boolean checkerTwo= Boolean.FALSE;
        for (FootballClub footballClub : league) {
            //checking it is in the league
            if (footballClub.getName().equalsIgnoreCase(teamTwo)) {
                //setting matches played plus one
                footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed()+1);
                //match.setClubOne(teamOne);
                //System.out.println(match.getClubOne());
                checkerTwo=true;
            }
        }
        //it is not in the league
        if (!checkerTwo){
            System.out.println("No matching clubs to "+ teamTwo + " in the league!!!...");
            return;
        }

        int teamOneGoals;
        int teamTwoGoals;
        System.out.println("Enter Team 1 no: of Goals : ");
        try {
            //checking if the input is an integer or not
            teamOneGoals = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Please Enter a numerical value!!!...");
            return;
        }
        System.out.println("Enter Team 2 no: of Goals : ");
        try {
            teamTwoGoals = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Please Enter a numerical value!!!...");
            return;
//                System.out.println("Do you want to enter Again ? (Y/N)");
//                String input =scanner.next();
//                if (input.equalsIgnoreCase("y")){
//                    continue;
//                }else {
//                    break;
//                }
        }
        //setting the attributes of match
        Match match = new Match(teamOne,teamTwo,teamOneGoals,teamTwoGoals,date);
        //adding match to the league
        matches.add(match);
        //System.out.println(match.getClubOneScore());

        for (FootballClub footballClub: league){
            if (footballClub.getName().equalsIgnoreCase(match.getClubOne())){
                //checking th match score
                if (match.getClubOneScore()> match.getClubTwoScore()){
                    //setting the win count plus one of winning team
                    footballClub.setNumberOfWins(footballClub.getNumberOfWins()+1);
                    //adding points to winning team
                    footballClub.setCurrentNumberOfPoints(footballClub.getCurrentNumberOfPoints()+10);
                }else if (match.getClubOneScore() < match.getClubTwoScore()){
                    //setting loss count plus one to lossing team
                    footballClub.setNumberOfLoss(footballClub.getNumberOfLoss()+1);
                }else {
                    //if match is draw setting draw count plus one
                    footballClub.setNumberOfDraws(footballClub.getNumberOfDraws()+1);
                    //adding points
                    footballClub.setCurrentNumberOfPoints(footballClub.getCurrentNumberOfPoints()+5);
                }
                //setting the scored goal count
                footballClub.setNumberOfGoalsScored(footballClub.getNumberOfGoalsScored()+teamOneGoals);
                //setting the recieved goal count
                footballClub.setNumberOfGoalsRecieved(footballClub.getNumberOfGoalsRecieved()+teamTwoGoals);
            }else if (footballClub.getName().equalsIgnoreCase(match.getClubTwo())){
                if (match.getClubOneScore()< match.getClubTwoScore()){
                    footballClub.setNumberOfWins(footballClub.getNumberOfWins()+1);
                    footballClub.setCurrentNumberOfPoints(footballClub.getCurrentNumberOfPoints()+10);
                }else if (match.getClubOneScore() > match.getClubTwoScore()){
                    footballClub.setNumberOfLoss(footballClub.getNumberOfLoss()+1);
                }else {
                    footballClub.setNumberOfDraws(footballClub.getNumberOfDraws()+1);
                    footballClub.setCurrentNumberOfPoints(footballClub.getCurrentNumberOfPoints()+5);
                }
                footballClub.setNumberOfGoalsScored(footballClub.getNumberOfGoalsScored()+teamTwoGoals);
                footballClub.setNumberOfGoalsRecieved(footballClub.getNumberOfGoalsRecieved()+teamOneGoals);
            }
        }


    }

    @Override
    public void saveDataToFile() {
        try {
            //creating the file output stream and setting the file for the league list
            FileOutputStream fileOutputStreamLeague=new FileOutputStream("./DataInTheArray.txt");
            //creating the object output stream for the league list
            ObjectOutputStream objectOutputStreamLeague=new ObjectOutputStream(fileOutputStreamLeague);
            //creating the file output stream and setting the file for the matches list
            FileOutputStream fileOutputStreamMatches=new FileOutputStream("./DataInTheArray2.txt");
            //creating the file output stream for the matches list
            ObjectOutputStream objectOutputStreamMatches=new ObjectOutputStream(fileOutputStreamMatches);
            //getting objects from league list
            for(FootballClub footballClub : league){
                if (footballClub!= null){
                    //adding the object to the file
                    objectOutputStreamLeague.writeObject(footballClub);
                }
            }
            //getting objects from the matches list
            for (Match match : matches){
                if (match!=null){
                    //adding t the file
                    objectOutputStreamMatches.writeObject(match);
                }
            }
            System.out.println("Data saved to the file Successfully!!");

            //closing object output streams streams
            objectOutputStreamLeague.close();
            objectOutputStreamMatches.close();
            //flushing the streams
            objectOutputStreamLeague.flush();
            objectOutputStreamMatches.flush();

            //closing the file output streams
            fileOutputStreamLeague.close();
            fileOutputStreamMatches.close();

        } catch (FileNotFoundException e) {
            //if file not found
            System.out.println("File not Found");
            //e.printStackTrace();
        }catch(Exception e){
            //if any other error occured
            System.out.println("Something went Wrong");
        }
    }

    @Override
    public void loadDataFromFile() throws IOException {

        try{
            //creating file input stream for league list and accessing the file
            FileInputStream fileInputStreamLeague=new FileInputStream("./DataInTheArray.txt");
            //creating object input stream for league list
            ObjectInputStream objectInputStreamLeague=new ObjectInputStream(fileInputStreamLeague);
            //creating file input stream for matches list and accessing the file
            FileInputStream fileInputStreamMatches=new FileInputStream("./DataInTheArray2.txt");
            //creating object input stream for matches list
            ObjectInputStream objectInputStreamMatches=new ObjectInputStream(fileInputStreamMatches);
            //creating a infinite for loop
            for (;;){
                try {
                    //reading the football club objects from the file
                    FootballClub footballClub = (FootballClub) objectInputStreamLeague.readObject();
                    //adding football club objects to the league list
                    league.add(footballClub);
                }catch (IOException e){
                    break;
                }
            }
            //creating a infinite for loop
            for (; ;){
                try {
                    //reading the match club objects from the file
                    Match match=(Match) objectInputStreamMatches.readObject();
                    //adding match club objects to the matches list
                    matches.add(match);
                }catch (IOException e){
                    break;
                }
            }
            //closing the streams
            objectInputStreamLeague.close();
            objectInputStreamMatches.close();
            System.out.println("Data imported Successfully!!!");
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("Something went Wrong");
        }
    }

    @Override
    public void randomMatchPicker(){
        if (league.isEmpty()){
            return;
        }
        //creating football club objects
        FootballClub teamA;
        FootballClub teamB;
        //creating the date object
        Date date=new Date();
        //crating a random number and assigning that index object into the variable
        teamA = league.get(new Random().nextInt(league.size()));
        teamB = league.get(new Random().nextInt(league.size()));
        //getting the team name using getters
        String teamAName = teamA.getName();
        String teamBName = teamB.getName();
        //checking the both randomly generated teams are same
        if(teamAName.equals(teamBName)){
            System.out.println("Retry!");
            return;}
        //creating random scores
        Random randomScore01 = new Random();
        int teamAScore = randomScore01.nextInt(5);
        Random randomScore02 = new Random();
        int teamBScore = randomScore02.nextInt(5);

        //setting the attributes of match
        Match match = new Match(teamAName,teamBName,teamAScore,teamBScore,date);
        //adding match to the league
        matches.add(match);

        for (FootballClub footballClub: league){
            if (footballClub.getName().equalsIgnoreCase(teamAName)){
                //checking th match score
                if (teamAScore> teamBScore){
                    //setting the win count plus one of winning team
                    footballClub.setNumberOfWins(footballClub.getNumberOfWins()+1);
                    //adding points to winning team
                    footballClub.setCurrentNumberOfPoints(footballClub.getCurrentNumberOfPoints()+10);
                }else if (teamAScore< teamBScore){
                    //setting loss count plus one to lossing team
                    footballClub.setNumberOfLoss(footballClub.getNumberOfLoss()+1);
                }else {
                    //if match is draw setting draw count plus one
                    footballClub.setNumberOfDraws(footballClub.getNumberOfDraws()+1);
                    //adding points
                    footballClub.setCurrentNumberOfPoints(footballClub.getCurrentNumberOfPoints()+5);
                }
                //setting the scored goal count
                footballClub.setNumberOfGoalsScored(footballClub.getNumberOfGoalsScored()+teamAScore);
                //setting the recieved goal count
                footballClub.setNumberOfGoalsRecieved(footballClub.getNumberOfGoalsRecieved()+teamBScore);
            }else if (footballClub.getName().equalsIgnoreCase(teamBName)){
                if (teamAScore< teamBScore){
                    footballClub.setNumberOfWins(footballClub.getNumberOfWins()+1);
                    footballClub.setCurrentNumberOfPoints(footballClub.getCurrentNumberOfPoints()+10);
                }else if (teamAScore> teamBScore){
                    footballClub.setNumberOfLoss(footballClub.getNumberOfLoss()+1);
                }else {
                    footballClub.setNumberOfDraws(footballClub.getNumberOfDraws()+1);
                    footballClub.setCurrentNumberOfPoints(footballClub.getCurrentNumberOfPoints()+5);
                }
                footballClub.setNumberOfGoalsScored(footballClub.getNumberOfGoalsScored()+teamBScore);
                footballClub.setNumberOfGoalsRecieved(footballClub.getNumberOfGoalsRecieved()+teamAScore);
            }
        }

        System.out.println("team one : "+teamAName+" team one score : "+teamAScore);
        System.out.println("team two : "+teamBName+" team two score : "+teamBScore);
        StackPane randomPane=new StackPane();
        //displaying the details using a label
        Label label=new Label("Date: " +date+"\nTeam one: "+teamAName +" scored "+teamAScore+" goals."+"\nTeam two: "
                +teamBName+" scored "+teamBScore+" goals." );
        label.setTextFill(Color.INDIGO);
        label.setFont(Font.font(null,FontWeight.SEMI_BOLD,20));
        randomPane.getChildren().addAll(label);
        Scene scene=new Scene(randomPane);
        Stage stage=new Stage();
        stage.setTitle("Random Match Picker");
        stage.setScene(scene);
        stage.setWidth(550);
        stage.setHeight(337);
        stage.showAndWait();
        }


    public ObservableList<FootballClub> points(){
        //sorting the lists descending order
        league.sort(Collections.reverseOrder());
        //creating a observable list of football clubs
        ObservableList<FootballClub>club= FXCollections.observableArrayList();
        for (FootballClub footballClub: league){
            //adding the all elements of league into club observable list
            club.addAll(footballClub);
        }
        //returning the list
        return club;
    }


    public ObservableList<FootballClub> goalsScored() {
        //creating a observable list of football clubs
        ObservableList <FootballClub> goalsScoredList=FXCollections.observableArrayList();
        //sorting the lists descending order according to the goals scored
        league.sort(Collections.reverseOrder(Comparator.comparing(FootballClub::getNumberOfGoalsScored)));
//        for (FootballClub club:league){
//            System.out.println(club.getName()+" "+club.getNumberOfGoalsScored());
//        }
        for (FootballClub club:league){
            //adding the all elements of league into observable list
            goalsScoredList.addAll(club);
        }
        return goalsScoredList;
    }

    public ObservableList<FootballClub> largestWins() {
        //creating a observable list of football clubs
        ObservableList <FootballClub> largestWinsList=FXCollections.observableArrayList();
        //sorting the lists descending order according to the number of wins
        league.sort(Collections.reverseOrder(Comparator.comparing(FootballClub::getNumberOfWins)));
//        for (FootballClub club:league){
//            System.out.println(club.getName()+" "+club.getNumberOfWins());
//        }
        for (FootballClub club:league){
            //adding the all elements of league into observable list
            largestWinsList.addAll(club);
        }
        return largestWinsList;
    }

    @Override
    public ObservableList<Match> accordingToDate() {
        //creating a observable list of match
        ObservableList <Match> accordingToDateList=FXCollections.observableArrayList();
        //sorting the list
        Collections.sort(matches);
//        for (Match match:matches){
//            System.out.println(match.getDate());
//        }
        for (Match match:matches){
            //adding the all elements into observable list
            accordingToDateList.addAll(match);
        }
        return accordingToDateList;
    }


    public ObservableList<Match> matchesPlayed(){
        //sorting the list
        Collections.sort(matches);
        //creating a observable list of match
        ObservableList<Match> matchPlay=FXCollections.observableArrayList();
        for (Match match : matches){
            //adding the all elements into observable list
            matchPlay.addAll(match);
        }
        return matchPlay;
    }

    public ObservableList<Match> matchDate(Date date){
        //creating a observable list of football clubs
        ObservableList<Match> matchOnThisDate=FXCollections.observableArrayList();

//        for (Match match: matches){
//
//            //checking the input date equals to match date
//            if (match.getDate().equals(date)){
//                //adding the all elements into observable list
//                matchOnThisDate.addAll(match);
//                System.out.println(match);
//            }
//        }
//        return matchOnThisDate;

        ObservableList<Match> search = FXCollections.observableArrayList();
        for (Match match : matches){
            if (match.getDate().equals(date)){
                search.addAll(match);
            }
        }
        return search;
    }


}
