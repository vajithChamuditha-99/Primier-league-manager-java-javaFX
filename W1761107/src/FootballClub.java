import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Serializable, Comparable<FootballClub> {

    //creating the attributes of football club

    private int numberOfWins;
    private int numberOfLoss;
    private int numberOfDraws;
    private int numberOfMatchesPlayed;
    private int numberOfGoalsScored;
    private int numberOfGoalsRecieved;
    private int currentNumberOfPoints;

    //creating the constructors

    public FootballClub(int numberOfWins, int numberOfLoss, int numberOfDraws, int numberOfMatchesPlayed,
                        int numberOfGoalsScored, int numberOfGoalsRecieved, int currentNumberOfPoints) {
        super();
        this.numberOfWins = numberOfWins;
        this.numberOfLoss = numberOfLoss;
        this.numberOfDraws = numberOfDraws;
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
        this.numberOfGoalsScored = numberOfGoalsScored;
        this.numberOfGoalsRecieved = numberOfGoalsRecieved;
        this.currentNumberOfPoints = currentNumberOfPoints;
    }

    public FootballClub() {
    }

    //creating getters and setters
    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfLoss() {
        return numberOfLoss;
    }

    public void setNumberOfLoss(int numberOfLoss) {
        this.numberOfLoss = numberOfLoss;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws(int numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    public int getNumberOfMatchesPlayed() {
        return numberOfMatchesPlayed;
    }

    public void setNumberOfMatchesPlayed(int numberOfMatchesPlayed) {
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
    }

    public int getNumberOfGoalsScored() {
        return numberOfGoalsScored;
    }

    public void setNumberOfGoalsScored(int numberOfGoalsScored) {
        this.numberOfGoalsScored = numberOfGoalsScored;
    }

    public int getNumberOfGoalsRecieved() {
        return numberOfGoalsRecieved;
    }

    public void setNumberOfGoalsRecieved(int numberOfGoalsRecieved) {
        this.numberOfGoalsRecieved = numberOfGoalsRecieved;
    }

    public int getCurrentNumberOfPoints() {
        return currentNumberOfPoints;
    }

    public void setCurrentNumberOfPoints(int currentNumberOfPoints) {
        this.currentNumberOfPoints = currentNumberOfPoints;
    }

    //overriding the toString method
    @Override
    public String toString() {
        return  "FootballClub { " + super.toString()+
                "Number Of Wins=" + numberOfWins +
                ", Number Of Losses=" + numberOfLoss +
                ", Number Of Draws=" + numberOfDraws +
                ", Number Of Matches Played=" + numberOfMatchesPlayed +
                ", Number Of Goals Scored=" + numberOfGoalsScored +
                ", Number Of Goals Recieved=" + numberOfGoalsRecieved +
                ", Current Number Of Points=" + currentNumberOfPoints +
                " } ";
    }
    //overriding the hashcode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),currentNumberOfPoints);
    }

    /*@Override
    public int compareTo(Object o) {
        FootballClub footballClub =(FootballClub) o;
        if (footballClub.currentNumberOfPoints >this.getCurrentNumberOfPoints()){
            return -1;
        }else if (footballClub.currentNumberOfPoints< this.getCurrentNumberOfPoints()){
            return 1;
        }else {
            return Integer.compare(this.getNumberOfGoalsScored() - this.getNumberOfGoalsRecieved(), footballClub.getNumberOfGoalsScored() - footballClub.getNumberOfGoalsRecieved());
        }
    }*/
    //overriding the compareTo method
    @Override
    public int compareTo(FootballClub o) {
        //comparing the points
        if (o.currentNumberOfPoints>this.getCurrentNumberOfPoints()){
            return -1;
        }else if (o.currentNumberOfPoints<this.getCurrentNumberOfPoints()){
            return 1;
        }else{
            //comparing with goals
            if ((o.getNumberOfGoalsScored()-o.getNumberOfGoalsRecieved())>(this.getNumberOfGoalsScored()-this.getNumberOfGoalsRecieved())){
                return -1;
            }else if ((o.getNumberOfGoalsScored())-o.getNumberOfGoalsRecieved()<(this.getNumberOfGoalsScored()-this.getNumberOfGoalsRecieved())){
                return 1;
            }else {
                return 0;
            }
        }
    }
}
