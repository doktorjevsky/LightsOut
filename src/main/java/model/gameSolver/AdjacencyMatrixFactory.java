package model.gameSolver;

import model.GridModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A helper class for solving a grid
 * */

public class AdjacencyMatrixFactory {


    /**
     * Returns the adjacency matrix to a grid of size "size" and reach "reach"
     *
     * For example, consider the 2x2 grid with reach = 1:
     * [s1 s2]
     * [s3 s4]
     *
     * It has the adjacency matrix:
     *  s1 s2 s3 s4
     * [1  1  1  0] = s1
     * [1  1  0  1] = s2
     * [1  0  1  1] = s3
     * [0  1  1  1] = s4
     *
     * @param reach the reach of the GridFunc, with wich the player is supposed to solve the grid
     * @param size the size of the grid that the player is supposed to solve
     * @return the adjacency matrix to the size*size grid with reach "reach"
     * */
    public  List<List<Integer>> getAdjacencyMatrix(int size, int reach){
        GridModel dummy = new GridModel(reach, genGrid(size));
        List<List<Integer>> m = new ArrayList<>();
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                dummy.toggleCell(j, i);
                List<Integer> row = getRow(dummy.getGrid());
                m.add(row);
                dummy.toggleCell(j, i);
            }
        }
        return m;
    }

    private List<Integer> getRow(List<List<Integer>> m){
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < m.size(); i++){
            for (int j = 0; j < m.get(0).size(); j++){
                row.add(m.get(i).get(j));
            }
        }
        return row;
    }

    private List<List<Integer>> genGrid(int size){
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < size; i++){
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < size; j++){
                row.add(0);
            }
            grid.add(row);
        }

        return grid;
    }


}
