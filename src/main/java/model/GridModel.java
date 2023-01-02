package model;

import model.modelInterfaces.Observable;
import model.modelInterfaces.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class for modelling the game.
 * */

public class GridModel implements Observable {

    private List<List<Integer>> grid;
    private List<Observer> observers = new ArrayList<>();
    private GridFunc f;

    /**
     * Requires: Reach is a non-negative integer, grid is a solvable grid that is not yet solved
     * Ensures: A player may solve the game only by applying the GridFunc on the grid
     * */

    public GridModel(int reach, List<List<Integer>> grid){
        this.grid = grid;
        f = new GridFunc(reach);
    }

    /**
     * Requires: (x, y) is within the grid's bounds
     * Ensures: GridFunc is applied without throwing errors
     *
     * @param x the column to toggle
     * @param y the row to toggle
     * */
    public void toggleCell(int x, int y){
        grid = f.apply(grid, x, y);
    }

    /**
     * @return true if all entries in grid are 0, else false
     * */
    public boolean lightsAreOut(){
        for (List<Integer> is : grid){
            for (Integer i : is){
                if (i != 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Ensures: modifying the returned grid does not modify the original grid
     * @return a copy of the grid
     * */
    public List<List<Integer>> getGrid(){
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> is : grid){
            List<Integer> newRow = new ArrayList<>(is);
            out.add(newRow);
        }
        return out;
    }

    public GridFunc getFunction(){ return f; }

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
