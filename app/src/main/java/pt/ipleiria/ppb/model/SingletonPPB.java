package pt.ipleiria.ppb.model;

import java.util.ArrayList;

public class SingletonPPB {
    private static final SingletonPPB ourInstance = new SingletonPPB();

    public static SingletonPPB getInstance() {
        return ourInstance;
    }

    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<Task> tasks = new ArrayList<>();
    private boolean editGame=false;

    private SingletonPPB() {


    }


    public boolean isEditGame() {
        return editGame;
    }

    public void setEditGame(boolean editGame) {
        this.editGame = editGame;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
