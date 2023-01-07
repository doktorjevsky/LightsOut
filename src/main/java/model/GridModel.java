package model;

import model.modelInterfaces.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class for modelling the game.
 * */

public class GridModel {

    private List<List<Integer>> grid;
    private List<Observer> observers = new ArrayList<>();
    private GridFunc f;

    /**
     * Requires: Reach is a non-negative integer, grid is a solvable grid that is not yet solved
     * Ensures: By only using GridFunc, it is possible to transform the grid to a grid only containing zeroes
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

    public void toggleCell(Point2D p){
        grid = f.apply(grid, p);
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


    public int getReach(){ return f.getReach(); }

}
