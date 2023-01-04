package model;

import controllers.commands.ICommand;
import controllers.commands.RunSolutionCommand;
import model.modelInterfaces.Observable;
import model.modelInterfaces.Observer;
import model.staticHelpers.RandomModelFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that functions as a simple interface between the controller and the model itself
 * */

public class GameFacade implements Observable {

    private GridModel model;
    private List<Observer> observers = new ArrayList<>();

    public GameFacade(){
        model = RandomModelFactory.buildModel(2, 2);
    }

    public List<List<Integer>> getGrid(){
        return model.getGrid();
    }

    public void toggleCell(int x, int y){
        model.toggleCell(x, y);
        notifyObservers();
    }

    public void toggleCell(Point2D p){
        model.toggleCell(p);
        notifyObservers();
    }

    public void newGame(){
        model = RandomModelFactory.buildModel(2, 2);
        notifyObservers();
    }

    public void newGame(int reach, int size){
        model = RandomModelFactory.buildModel(reach, size);
        notifyObservers();
    }

    public void solveGrid(){
        ICommand c = new RunSolutionCommand(this);
        c.apply();
    }

    public int getReach(){ return model.getReach(); }

    public boolean lightsAreOut(){
        return model.lightsAreOut();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void removeAll() {
        observers = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update();
        }
    }
}
