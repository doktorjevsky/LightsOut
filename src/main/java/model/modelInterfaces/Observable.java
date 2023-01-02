package model.modelInterfaces;

public interface Observable {

    void addObserver(Observer o);

    void removeObserver(Observer o);

    void removeAll();

    void notifyObservers();


}
