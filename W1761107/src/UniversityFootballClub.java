public class UniversityFootballClub extends FootballClub {
    //creating the attributes
    private String universityName;
    private String universityLocation;


    //creating the constructor
    public UniversityFootballClub(int numberOfWins, int numberOfLoss, int numberOfDraws, int numberOfMatchesPlayed, int numberOfGoalsScored, int numberOfGoalsRecieved, int currentNumberOfPoints) {
        super(numberOfWins, numberOfLoss, numberOfDraws, numberOfMatchesPlayed, numberOfGoalsScored, numberOfGoalsRecieved, currentNumberOfPoints);
    }

    //creating getters and setters
    public String getUniversityLocation() {
        return universityLocation;
    }

    public void setUniversityLocation(String universityLocation) {
        this.universityLocation = universityLocation;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }


    //overriding the toString method
    @Override
    public String toString() {
        return "University Football Club{" +
                "universityName='" + universityName + '\'' +
                ", universityLocation='" + universityLocation + '\''+
                super.toString() + '}';
    }
}
