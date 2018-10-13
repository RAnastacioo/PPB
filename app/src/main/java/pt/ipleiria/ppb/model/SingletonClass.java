package pt.ipleiria.ppb.model;


public class SingletonClass {

    private static volatile SingletonClass sSoleInstance = new SingletonClass();

    //private constructor.
    private SingletonClass(){}

    public static SingletonClass getInstance() {


        return sSoleInstance;
    }
}