package pt.ipleiria.ppb.model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Game {


    private String id;
    private String title;
    private String description;
    private String Author;
    private String lastUpdate;
    private int durationGame;
    private ArrayList<Task> tasks = new ArrayList<>();

    public Game(String title, String description, String Author, int durationGame) {
        this.id =UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.Author = Author;
        this.durationGame = durationGame;
        this.lastUpdate = getDateString();
    }

    public String getDateString() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddd/MM/yyyy");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        return date;
    }

    public Date getDate() {
        Date date = Calendar.getInstance().getTime();
        return date;
    }

    public String getId() {
        return id;
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

    public String getAuthor() {
        return Author;
    }

    public void setUser(String Author) {
        this.Author = Author;
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
