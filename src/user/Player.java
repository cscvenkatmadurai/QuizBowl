package user;

/**
 * Created by HARISH on 7/5/2015.
 */
public class Player {
    protected String firstName;
    protected String lastName;
    protected int points;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        points = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void addPoints(int extraPoints){
        points +=extraPoints;
    }
}
