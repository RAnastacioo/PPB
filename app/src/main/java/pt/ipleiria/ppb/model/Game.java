package pt.ipleiria.ppb.model;
import java.util.ArrayList;
import java.util.Calendar;

public class Game {


    private int id;
    private String title;
    private String description;
    private User user;
    private Calendar dateLastedit;
    private int durationGame;
    private ArrayList<Task> tasks = new ArrayList<>();


    public Game(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateLastedit = dateLastedit;

    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getDateLastedit() {
        return dateLastedit;
    }

    public int getDurationGame() {
        return durationGame;
    }
}
