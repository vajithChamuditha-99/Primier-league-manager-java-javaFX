public class SchoolFootballClub extends FootballClub {
    //creating school football club attributes
    private String schoolName;
    private String schoolLocation;

        //creating constructors
    public SchoolFootballClub(String schoolName, String schoolLocation) {
        super();
        this.schoolName = schoolName;
        this.schoolLocation = schoolLocation;
    }
    //creating getters and setters
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolLocation() {
        return schoolLocation;
    }

    public void setSchoolLocation(String schoolLocation) {
        this.schoolLocation = schoolLocation;
    }

    //overriding the toString method
    @Override
    public String toString() {
        return "SchoolFootballClub{" +
                "schoolName='" + schoolName + '\'' +
                ", schoolLocation='" + schoolLocation + '\''
                + super.toString()+ '}';
    }


}
