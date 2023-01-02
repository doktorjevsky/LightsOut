package model;

import model.modelInterfaces.Observable;
import model.modelInterfaces.Observer;

import java.util.List;

public class GridModel implements Observable {

    private List<List<Integer>> grid;
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

    @Override
    public void addObserver(Observer o) {
        
    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void removeAll() {

    }

    @Override
    public void notifyObservers() {

    }
}
