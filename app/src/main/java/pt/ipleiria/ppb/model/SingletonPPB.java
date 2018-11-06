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

    public Task containsID(String id, Game game) {

        for (int i = 0; i < game.getTasks().size(); i++) {
            if (game.getTasks().get(i).getId().equals(id)) {
                return game.getTasks().get(i);
            }

        }
        return null;
    }

    public Task containsIDTask(String id) {

        for (int i = 0; i < games.size(); i++) {

            for (int j = 0; j < games.get(i).getTasks().size(); j++) {

                if (games.get(i).getTasks().get(j).getId().equals(id)) {

                    return games.get(i).getTasks().get(j);
                }
            }
        }
        return null;
    }
}
