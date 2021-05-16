import java.io.Serializable;
import java.util.Date;


public class Match implements Serializable,Comparable<Match> {
    //setting the match attributes
    private String clubOne;
    private String clubTwo;
    private int clubOneScore;
    private int clubTwoScore;
    private Date date;

    //setting the match constructors
    public Match(String name, String name1, int clubOneScore, int clubTwoScore,Date date) {
        this.clubOne = name;
        this.clubTwo = name1;
        this.clubOneScore = clubOneScore;
        this.clubTwoScore = clubTwoScore;
        this.date=date;
    }

    public Match() {

    }

    //creating the getters and setters
    public String getClubOne() {
        return clubOne;
    }

    public void setClubOne(String clubOne) {
        this.clubOne = clubOne;
    }

    public String getClubTwo() {
        return clubTwo;
    }

    public void setClubTwo(String clubTwo) {
        this.clubTwo = clubTwo;
    }

    public int getClubOneScore() {
        return clubOneScore;
    }

    public void setClubOneScore(int clubOneScore) {
        this.clubOneScore = clubOneScore;
    }

    public int getClubTwoScore() {
        return clubTwoScore;
    }

    public void setClubTwoScore(int clubTwoScore) {
        this.clubTwoScore = clubTwoScore;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date=date;
    }
    //overriding compareTo method to compare the dates
    @Override
    public int compareTo(Match clubOne) {
        return this.date.compareTo(clubOne.getDate());
    }

}
