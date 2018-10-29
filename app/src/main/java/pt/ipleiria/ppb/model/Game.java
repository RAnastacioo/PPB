package pt.ipleiria.ppb.model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Game implements Serializable {


    private String id;
    private String title;
    private String description;
    private String Author;
    private String lastUpdate;
    private int durationGame;
    private ArrayList<Task> tasks;

    public Game(String title, String description, String Author, int durationGame) {
        this.id =UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.Author = Author;
        this.durationGame = durationGame;
        this.lastUpdate = getDateString();
        this.tasks = new ArrayList<>();
    }

    public String getDateString() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss");
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

    public void setId(String id) {
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

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
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

}
