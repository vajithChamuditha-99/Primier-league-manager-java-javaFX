import java.io.Serializable;
//making the sports club class abstract
public abstract class SportsClub implements Serializable {
    //creating the sports club attributes
    private String name;
    private String location;

    //creating the constructors

    public SportsClub(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public SportsClub() {

    }

    //creating getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    //overriding the toSting method
    @Override
    public String toString() {
        return " SportsClub {" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                "} ";
    }
}
