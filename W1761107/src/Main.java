import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main extends Application {

    private static Scanner scanner=new Scanner(System.in);  //creating a global scanner
    static LeagueManager leagueManager= new PremierLeagueManager();

    public static void main(String[] args) throws ParseException, IOException {
        String green="\u001B[32m"; //for colors in text
        String def="\u001B[0m";
        leagueManager.loadDataFromFile();

        System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println(green+" ++ Welcome to Premier league management  System ++ "+def);
        System.out.println("******************************************************");
        System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
        System.out.println("");



        Application.launch();

    }

    private static void displayMenu(){

        String green="\u001B[32m"; //for colors in text
        String def="\u001B[0m";
        String blue="\u001B[34m";
        //String

        menu:
        //infinite loop
        while (true){
            System.out.println("");
            System.out.println(blue+"Press 1 to Add a Club to the League");
            System.out.println("Press 2 to Delete a Club from the League");
            System.out.println("Press 3 to Print Statistics");
            System.out.println("Press 4 to Display Sports club List");
            System.out.println("Press 5 to Display League Table");
            System.out.println("Press 6 to Add match Details");
            System.out.println("Press 7 to Display GUI");
            System.out.println("Press 8 to Save Details in a Text File");
            System.out.println("Press 9 to Load Details from the Text File");
            System.out.println("Press 0 to Quit from the Application"+def);
            System.out.println("Enter your choice : ");
            int choice = 0;
            boolean flag =Boolean.TRUE;
            while (flag){
                //checking input is an integer or not using try catch
                try {
                    choice=scanner.nextInt();

                    switch (choice){
                        case 1:
                            addSportsClub();
                            break;
                        case 2:
                            deleteSportsClub();
                            break;
                        case 3:
                            printStatistics();
                            break;
                        case 4:
                            printSportsClubList();
                            break;
                        case 5:
                            leagueManager.displayLeagueTable();
                            break;
                        case 6:

                            leagueManager.addMatchDetails();
                            break;
                        case 7:
                            GUI();
                            break ;
                        case 0:
                            leagueManager.saveDataToFile();
                            System.out.println("Thank You for using the System. Have a Pleasant Day!");
                            break menu;
                        default:
                            System.out.println("You Selected an Invalid Option. Please Try Again!");
                            displayMenu();
                    }
                }catch (InputMismatchException | IOException e){
                    System.out.println("Please enter valid option!!!...");
                    System.out.println("Do you wat to enter again (Y/N)");
                    String input =scanner.next();
                    if (input.equalsIgnoreCase("y")){


                    }else {
                        break ;
                    }
                }
                flag=false;
            }
        }
    }

    private static void addSportsClub(){
        //creating a football club object
        FootballClub footballClub=new FootballClub();
        //getting user inputs
        System.out.println("Enter the name of the Club : ");
        String name=scanner.next();
        //setting values using setters
        footballClub.setName(name);
        System.out.println("Enter the location of the Club : ");
        String location=scanner.next();
        footballClub.setLocation(location);

        leagueManager.addSportsClub(footballClub);

    }

    private static void deleteSportsClub(){
        System.out.println("Enter the Club name do you want to Delete");
        String clubName=scanner.next();
        System.out.println("Enter the Club location do you want to Delete");
        String clubLocation=scanner.next();
        leagueManager.deleteSportsClub(clubName,clubLocation);
    }

    private static void printSportsClubList(){
        leagueManager.printSportsClubList();
    }

    private static void printStatistics(){
        System.out.println("Enter a Club name to show Statistics");
        String clubName=scanner.next();
        leagueManager.printStatistics(clubName);
    }


    private static void backToMenu(){
        System.out.println("Press \"Y\" to continue with main menu, press any other key to exit:..");
        String input=scanner.nextLine();
        if (input.equalsIgnoreCase("y")){
            displayMenu();
        }else {
            System.exit(0);
        }
    }

    private static void GUI()throws IOException{
        //creating the gui
        Stage mainStage = new Stage();
        Label lbl1 = new Label("Premier league management system");
        lbl1.setFont(Font.font(25));
        Label searchDate=new Label("Pick a date from below to search");
        TextField txt=new TextField();

        //searchDate.setText("search match using date");
        Button searchOk = new Button("Search match from Date");

        searchOk.setOnAction(event -> {
            String dt=txt.getText();
            if (dt==null){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please enter a date to search");
                alert.show();
                return;
            }
            txt.setText(null);
            Date date;
            try {
                //converting the string into Date
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                System.out.println(date);
            } catch (ParseException e) {
                System.out.println("You have to enter date in format yyyy-MM-dd");
                return;
            }
            //creating the table in the gui
            TableView<Match> searchTableView = new TableView<>();
            searchTableView.setMinWidth(600);
            TableColumn<Match, Integer> dateClm = new TableColumn<>("Date");
            dateClm.setCellValueFactory(new PropertyValueFactory<>("date"));

            TableColumn<Match, String> team1 = new TableColumn<>("Team A Name");
            team1.setMinWidth(120);
            team1.setStyle("-fx-alignment:CENTER;");
            team1.setCellValueFactory(new PropertyValueFactory<>("clubOne"));

            TableColumn<Match, String> team2 = new TableColumn<>("Team b Name");
            team2.setMinWidth(120);
            team1.setStyle("-fx-alignment:CENTER;");
            team2.setCellValueFactory(new PropertyValueFactory<>("clubTwo"));

            TableColumn<Match, String> team1Score = new TableColumn<>("Team A Score");
            team1Score.setCellValueFactory(new PropertyValueFactory<>("clubOneScore"));
            team1Score.setStyle("-fx-alignment:CENTER;");

            TableColumn<Match, String> team2Score = new TableColumn<>(" Team B Score ");
            team2Score.setCellValueFactory(new PropertyValueFactory<>("clubTwoScore"));
            team2Score.setStyle("-fx-alignment:CENTER;");
            //setting the items
            searchTableView.setItems(leagueManager.matchDate(date));
            //adding the columns to the table
            searchTableView.getColumns().addAll(dateClm,team1,team2,team1Score,team2Score);

            Button buttonClose=new Button("Back to main GUI");
            Stage stage1=new Stage();
            buttonClose.setOnAction(event1 -> {
                //closing the stage
                stage1.close();
                mainStage.show();
            });
            VBox vBox=new VBox();
            buttonClose.setFont(Font.font(null,FontWeight.BOLD,10));
            buttonClose.setStyle("-fx-background-color: #CF541F ; -fx-text-fill: white");
            buttonClose.setPrefHeight(35);
            Label label=new Label("Search match from Date");
            //setting the text color
            label.setTextFill(Color.BLUE);
            //decorating the text
            label.setFont(Font.font(null, FontWeight.BOLD,30));
            //adding items to v anchor pane
            label.setLayoutY(20);
            label.setLayoutX(190);
            buttonClose.setLayoutY(540);
            buttonClose.setLayoutX(470);
            vBox.setLayoutY(92);
            vBox.setLayoutX(40);
            vBox.setSpacing(5);
            vBox.setMaxWidth(450);
            vBox.getChildren().addAll(searchTableView);
            Scene scene1=new Scene(new Group());
            //adding items to scene
            ((Group) scene1.getRoot()).getChildren().addAll(label,vBox,buttonClose);
            scene1.setFill(Color.web("#D3D3D3"));
            stage1.setTitle("Search Match Date");
            stage1.setScene(scene1);
            stage1.setWidth(700);
            stage1.setHeight(650);
            stage1.showAndWait();


        });
        Button randomPlayer = new Button("Pick random match ");
        //random match picker action event
        randomPlayer.setOnAction(event -> {
            try {
                leagueManager.randomMatchPicker();
            } catch (IOException e) {
                System.out.println("Oops!!!");
            }
        });
        Button playedMatches = new Button("Display played matches");
        //display played matches action event
        playedMatches.setOnAction(event -> {
            Stage playedMatchStage=new Stage();
            playedMatchStage.setTitle("Points Table");
            //creating the table
            TableView<Match> matchTableView=new TableView<>();
            //setting the table minimum width
            matchTableView.setMinWidth(670);
            //creating the columns and setting their data types
            TableColumn<Match, Integer> dateClm = new TableColumn<>("Date");
            //accessing values from the list
            dateClm.setCellValueFactory(new PropertyValueFactory<>("date"));
            TableColumn<Match, Integer> teamOne = new TableColumn<>("Team One");
            teamOne.setCellValueFactory(new PropertyValueFactory<>("clubOne"));
            teamOne.setMinWidth(140);
            //setting the components alignment
            teamOne.setStyle("-fx-alignment:CENTER;");
            TableColumn<Match, Integer> teamTwo = new TableColumn<>("Team Two");
            teamTwo.setCellValueFactory(new PropertyValueFactory<>("clubTwo"));
            teamTwo.setMinWidth(140);
            teamTwo.setStyle("-fx-alignment:CENTER;");
            TableColumn<Match, Integer> teamOneGoal = new TableColumn<>("Team One Score");
            teamOneGoal.setCellValueFactory(new PropertyValueFactory<>("clubOneScore"));
            teamOneGoal.setStyle("-fx-alignment:CENTER;");
            TableColumn<Match, Integer> teamTwoGoal = new TableColumn<>("Team Two Score");
            teamTwoGoal.setCellValueFactory(new PropertyValueFactory<>("clubTwoScore"));
            teamTwoGoal.setStyle("-fx-alignment:CENTER;");
            //setting the items
            matchTableView.setItems(leagueManager.matchesPlayed());
            //adding the columns to table view
            matchTableView.getColumns().addAll(dateClm,teamOne,teamTwo,teamOneGoal,teamTwoGoal);
            //close button
            Button buttonClose=new Button("Back to main GUI");
            buttonClose.setOnAction(event1 -> {
                playedMatchStage.close();
                mainStage.show();
            });
            Label labelPoints=new Label("Played Matches");
            //setting the text color
            labelPoints.setTextFill(Color.BLUE);
            //decorating the text
            labelPoints.setFont(Font.font(null, FontWeight.BOLD,30));
            labelPoints.setLayoutY(20);
            labelPoints.setLayoutX(280);
            buttonClose.setLayoutY(540);
            buttonClose.setLayoutX(470);
            //decorating button
            buttonClose.setFont(Font.font(null,FontWeight.BOLD,10));
            buttonClose.setStyle("-fx-background-color: #CF541F ; -fx-text-fill: white");
            buttonClose.setPrefHeight(35);
            VBox vbox = new VBox();
            vbox.setLayoutY(92);
            vbox.setLayoutX(40);
            vbox.setSpacing(5);
            vbox.setMaxWidth(450);
            //vbox.setPadding(new Insets(10, 0, 0, 30));
            //setting a background color to vbox
            vbox.setStyle("-fx-background-color:#ffffff");
            //adding the table view to the vbox
            vbox.getChildren().addAll(matchTableView);

            playedMatchStage.setWidth(780);
            playedMatchStage.setHeight(650);
            Scene scene = new Scene(new Group());
            //setting the scene color
            scene.setFill(Color.web("#D3D3D3"));
            //adding the components to scene
            ((Group) scene.getRoot()).getChildren().addAll(labelPoints,vbox,buttonClose);
            playedMatchStage.setScene(scene);
            playedMatchStage.showAndWait();
        });
        Button pointsButton=new Button("Points Table");
        //points button action event
        pointsButton.setOnAction(event -> {
            Stage goalsStage=new Stage();
            goalsStage.setTitle("Points Table");
            //creating the table
            TableView<FootballClub> matchTableView=new TableView<>();
            //setting the minimum width
            matchTableView.setMinWidth(892);
            TableColumn<FootballClub, String> nameClm = new TableColumn<>("Club name");
            //setting the items to the columns from the list
            nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
            nameClm.setMinWidth(140);
            nameClm.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> locationClm = new TableColumn<>("Location");
            locationClm.setCellValueFactory(new PropertyValueFactory<>("location"));
            locationClm.setMinWidth(140);
            locationClm.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> matchNumber = new TableColumn<>("No: of Matches");
            matchNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfMatchesPlayed"));
            matchNumber.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> winNumber = new TableColumn<>("No: of Wins");
            winNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
            winNumber.setStyle("-fx-alignment:CENTER;");

            TableColumn<FootballClub, Integer> lossNumber = new TableColumn<>("No: of Losses");
            lossNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfLoss"));
            lossNumber.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> drawNumber = new TableColumn<>("No: of Draws");
            drawNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfDraws"));
            drawNumber.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> scoredGoals = new TableColumn<>("Goals Scored");
            scoredGoals.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScored"));
            scoredGoals.setStyle("-fx-alignment:CENTER;");
            //scoredGoals.setStyle("");
            TableColumn<FootballClub, Integer> receivedGoals = new TableColumn<>("Goals Received");
            receivedGoals.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsRecieved"));
            receivedGoals.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> pointNumber = new TableColumn<>(" Points ");
            pointNumber.setCellValueFactory(new PropertyValueFactory<>("currentNumberOfPoints"));
            pointNumber.setStyle("-fx-alignment:CENTER;");
            matchTableView.setItems(leagueManager.points());
            matchTableView.getColumns().addAll(nameClm,locationClm,matchNumber,winNumber,lossNumber,drawNumber,scoredGoals,receivedGoals,pointNumber);

            Button buttonClose=new Button("Back to main GUI");
            buttonClose.setOnAction(event1 -> {
                goalsStage.close();
                mainStage.show();
            });
            Label labelPoints=new Label("Points Table");
            labelPoints.setTextFill(Color.BLUE);
            labelPoints.setFont(Font.font(null, FontWeight.BOLD,30));
            labelPoints.setLayoutY(20);
            labelPoints.setLayoutX(400);
            buttonClose.setLayoutY(540);
            buttonClose.setLayoutX(670);
            buttonClose.setFont(Font.font(null,FontWeight.BOLD,10));
            buttonClose.setStyle("-fx-background-color: #CF541F ; -fx-text-fill: white");
            buttonClose.setPrefHeight(35);
            final VBox vbox = new VBox();
            vbox.setLayoutY(92);
            vbox.setLayoutX(40);
            vbox.setSpacing(5);
            vbox.setMaxWidth(892);
            //vbox.setPadding(new Insets(10, 0, 0, 30));
            vbox.setStyle("-fx-background-color:#ffffff");
            vbox.getChildren().addAll(matchTableView);

            goalsStage.setWidth(990);
            goalsStage.setHeight(650);
            Scene scene = new Scene(new Group());
            scene.setFill(Color.web("#D3D3D3"));
            ((Group) scene.getRoot()).getChildren().addAll(labelPoints,vbox,buttonClose);
            goalsStage.setScene(scene);
            goalsStage.showAndWait();
        });
        //creating a split menu button
        SplitMenuButton splitMenuButton=new SplitMenuButton();
        splitMenuButton.setText("Sort Points table...");
        //creating menu item
        MenuItem goalsScored=new MenuItem("Goals Scored                    ");
        //menu item action event
        goalsScored.setOnAction(event -> {
            Stage goalsStage=new Stage();
            goalsStage.setTitle("Sort according to Goals Scored");
            TableView<FootballClub> matchTableView=new TableView<>();
            matchTableView.setMinWidth(892);
            TableColumn<FootballClub, String> nameClm = new TableColumn<>("Club name");
            nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
            nameClm.setMinWidth(140);
            nameClm.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> locationClm = new TableColumn<>("Location");
            locationClm.setCellValueFactory(new PropertyValueFactory<>("location"));
            locationClm.setMinWidth(140);
            locationClm.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> matchNumber = new TableColumn<>("No: of Matches");
            matchNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfMatchesPlayed"));
            matchNumber.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> winNumber = new TableColumn<>("No: of Wins");
            winNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
            winNumber.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> lossNumber = new TableColumn<>("No: of Losses");
            lossNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfLoss"));
            lossNumber.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> drawNumber = new TableColumn<>("No: of Draws");
            drawNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfDraws"));
            drawNumber.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> scoredGoals = new TableColumn<>("Goals Scored");
            scoredGoals.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScored"));
            scoredGoals.setStyle("-fx-alignment:CENTER;"+"-fx-font-weight:bold;"+"-fx-text-fill:red;");
            //scoredGoals.setStyle("");
            TableColumn<FootballClub, Integer> receivedGoals = new TableColumn<>("Goals Received");
            receivedGoals.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsRecieved"));
            receivedGoals.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> pointNumber = new TableColumn<>(" Points ");
            pointNumber.setCellValueFactory(new PropertyValueFactory<>("currentNumberOfPoints"));
            pointNumber.setStyle("-fx-alignment:CENTER;");
            matchTableView.setItems(leagueManager.goalsScored());
            matchTableView.getColumns().addAll(nameClm,locationClm,matchNumber,winNumber,lossNumber,drawNumber,scoredGoals,receivedGoals,pointNumber);

            Button buttonClose=new Button("Back to main GUI");
            buttonClose.setOnAction(event1 -> {
                goalsStage.close();
                mainStage.show();
            });
            Label labelPoints=new Label("Points Table");
            labelPoints.setTextFill(Color.BLUE);
            labelPoints.setFont(Font.font(null, FontWeight.BOLD,30));
            labelPoints.setLayoutY(20);
            labelPoints.setLayoutX(400);
            buttonClose.setLayoutY(540);
            buttonClose.setLayoutX(670);
            buttonClose.setFont(Font.font(null,FontWeight.BOLD,10));
            buttonClose.setStyle("-fx-background-color: #CF541F ; -fx-text-fill: white");
            buttonClose.setPrefHeight(35);
            final VBox vbox = new VBox();
            vbox.setLayoutY(92);
            vbox.setLayoutX(40);
            vbox.setSpacing(5);
            vbox.setMaxWidth(892);
            //vbox.setPadding(new Insets(10, 0, 0, 30));
            vbox.setStyle("-fx-background-color:#ffffff");
            vbox.getChildren().addAll(matchTableView);

            goalsStage.setWidth(990);
            goalsStage.setHeight(650);
            Scene scene = new Scene(new Group());
            scene.setFill(Color.web("#D3D3D3"));
            ((Group) scene.getRoot()).getChildren().addAll(labelPoints,vbox,buttonClose);
            goalsStage.setScene(scene);
            goalsStage.showAndWait();

        });
        //creating menu item
        MenuItem largestWins=new MenuItem("Largest Wins");
        //menuitem action event
        largestWins.setOnAction(event -> {
            Stage goalsStage=new Stage();
            //state title
            goalsStage.setTitle("Sort according to Largest Wins");
            TableView<FootballClub> matchTableView=new TableView<>();
            matchTableView.setMinWidth(892);
            TableColumn<FootballClub, String> nameClm = new TableColumn<>("Club name");
            nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
            nameClm.setMinWidth(140);
            nameClm.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> locationClm = new TableColumn<>("Location");
            locationClm.setCellValueFactory(new PropertyValueFactory<>("location"));
            locationClm.setMinWidth(140);
            locationClm.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> matchNumber = new TableColumn<>("No: of Matches");
            matchNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfMatchesPlayed"));
            matchNumber.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> winNumber = new TableColumn<>("No: of Wins");
            winNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
            winNumber.setStyle("-fx-alignment:CENTER;");
            winNumber.setStyle("-fx-alignment:CENTER;"+"-fx-font-weight:bold;"+"-fx-text-fill:red;");
            TableColumn<FootballClub, Integer> lossNumber = new TableColumn<>("No: of Losses");
            lossNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfLoss"));
            lossNumber.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> drawNumber = new TableColumn<>("No: of Draws");
            drawNumber.setCellValueFactory(new PropertyValueFactory<>("numberOfDraws"));
            drawNumber.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> scoredGoals = new TableColumn<>("Goals Scored");
            scoredGoals.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScored"));
            //scoredGoals.setStyle("");
            scoredGoals.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> receivedGoals = new TableColumn<>("Goals Received");
            receivedGoals.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsRecieved"));
            receivedGoals.setStyle("-fx-alignment:CENTER;");
            TableColumn<FootballClub, Integer> pointNumber = new TableColumn<>(" Points ");
            pointNumber.setCellValueFactory(new PropertyValueFactory<>("currentNumberOfPoints"));
            pointNumber.setStyle("-fx-alignment:CENTER;");
            matchTableView.setItems(leagueManager.largestWins());
            //adding table columns to table view
            matchTableView.getColumns().addAll(nameClm,locationClm,matchNumber,winNumber,lossNumber,drawNumber,scoredGoals,receivedGoals,pointNumber);

            Button buttonClose=new Button("Back to main GUI");
            //close button action event
            buttonClose.setOnAction(event1 -> {
                goalsStage.close();
                mainStage.show();
            });
            //decorations and placements
            Label labelPoints=new Label("Points Table");
            labelPoints.setTextFill(Color.BLUE);
            labelPoints.setFont(Font.font(null, FontWeight.BOLD,30));
            labelPoints.setLayoutY(20);
            labelPoints.setLayoutX(400);
            buttonClose.setLayoutY(540);
            buttonClose.setLayoutX(670);
            buttonClose.setFont(Font.font(null,FontWeight.BOLD,10));
            buttonClose.setStyle("-fx-background-color: #CF541F ; -fx-text-fill: white");
            buttonClose.setPrefHeight(35);
            final VBox vbox = new VBox();
            vbox.setLayoutY(92);
            vbox.setLayoutX(40);
            vbox.setSpacing(5);
            vbox.setMaxWidth(892);
            //vbox.setPadding(new Insets(10, 0, 0, 30));
            vbox.setStyle("-fx-background-color:#ffffff");
            vbox.getChildren().addAll(matchTableView);

            goalsStage.setWidth(990);
            goalsStage.setHeight(650);
            Scene scene = new Scene(new Group());
            scene.setFill(Color.web("#D3D3D3"));
            ((Group) scene.getRoot()).getChildren().addAll(labelPoints,vbox,buttonClose);
            goalsStage.setScene(scene);
            goalsStage.showAndWait();
        });
        splitMenuButton.getItems().addAll(goalsScored,largestWins);

        Button backToMenu = new Button("Back to Menu");
        searchDate.setLayoutY(277);
        searchDate.setLayoutX(161);
        lbl1.setLayoutX(161);
        lbl1.setLayoutY(38);
        searchOk.setLayoutX(161);
        searchOk.setLayoutY(217);
        searchOk.setFont(Font.font(null,FontWeight.BOLD,12));
        searchOk.setStyle("-fx-background-color:#009999;-fx-text-fill:white;");
        searchOk.setPrefSize(155,34);
        randomPlayer.setLayoutX(432);
        randomPlayer.setLayoutY(138);
        randomPlayer.setFont(Font.font(null,FontWeight.BOLD,12));
        randomPlayer.setStyle("-fx-background-color:#009999;-fx-text-fill:white;");
        randomPlayer.setPrefSize(155,34);
        playedMatches.setLayoutX(161);
        playedMatches.setLayoutY(138);
        playedMatches.setFont(Font.font(null,FontWeight.BOLD,12));
        playedMatches.setStyle("-fx-background-color:#009999;-fx-text-fill:white;");
        playedMatches.setPrefSize(155,34);
        pointsButton.setLayoutX(432);
        pointsButton.setLayoutY(217);
        pointsButton.setPrefSize(155,34);
        pointsButton.setFont(Font.font(null,FontWeight.BOLD,12));
        pointsButton.setStyle("-fx-background-color:#009999;-fx-text-fill:white;");
        backToMenu.setLayoutX(550);
        backToMenu.setLayoutY(462);
        backToMenu.setFont(Font.font(null,FontWeight.BOLD,12));
        backToMenu.setStyle("-fx-background-color: #CF541F ; -fx-text-fill: white");
        backToMenu.setPrefSize(120,35);
        txt.setLayoutX(161);
        txt.setLayoutY(310);
        txt.setPromptText("yyyy-MM-DD");
        splitMenuButton.setLayoutX(432);
        splitMenuButton.setLayoutY(309);
        splitMenuButton.setPrefSize(155,34);
        AnchorPane anchorPane = new AnchorPane();

        anchorPane.getChildren().addAll(lbl1,backToMenu,searchOk,randomPlayer,playedMatches,searchDate,pointsButton,txt,splitMenuButton);

        Scene scene = new Scene(anchorPane, 737, 550);
        mainStage.setScene(scene);
        anchorPane.setStyle("-fx-background-color:#b3b3cc");
        backToMenu.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> mainStage.close());
        mainStage.setTitle("Premier league management system");
        //setting stage into non resizable
        mainStage.setResizable(false);
        //showing the main stage
        mainStage.showAndWait();


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //displaying the menu
        displayMenu();
    }
}
