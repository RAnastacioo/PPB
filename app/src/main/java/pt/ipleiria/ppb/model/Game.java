package pt.ipleiria.ppb.model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Game {


    private int id;
    private String title;
    private String description;
    private String user;
    private String lastUpdate;
    private int durationGame;
    private ArrayList<Task> tasks = new ArrayList<>();

    public Game(String title, String description, String user, int durationGame) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.durationGame = durationGame;
        this.lastUpdate = getDateString();
    }

    public String getDateString() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        return date;
    }

    public Date getDate() {
        Date date = Calendar.getInstance().getTime();

        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getDurationGame() {
        return durationGame;
    }

    public void setDurationGame(int durationGame) {
        this.durationGame = durationGame;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
