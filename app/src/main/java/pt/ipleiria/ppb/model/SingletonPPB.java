package pt.ipleiria.ppb.model;

import java.util.ArrayList;

public class SingletonPPB {
    private static final SingletonPPB ourInstance = new SingletonPPB();

    public static SingletonPPB getInstance() {
        return ourInstance;
    }

    private ArrayList<Game> games = new ArrayList<>();


    private SingletonPPB() {


    }


    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public Game containsID(String id) {

        for (Game g : games) {
            if (g.getId().equals(id)) {
                return g;
            }
        }
        return null;
    }

}
