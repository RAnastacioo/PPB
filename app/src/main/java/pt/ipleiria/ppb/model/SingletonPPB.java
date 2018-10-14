package pt.ipleiria.ppb.model;

import java.util.ArrayList;

public class SingletonPPB {
    private static final SingletonPPB ourInstance = new SingletonPPB();

    public static SingletonPPB getInstance() {
        return ourInstance;
    }

    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<Task> tasks = new ArrayList<>();

    private SingletonPPB() {


    }
}
