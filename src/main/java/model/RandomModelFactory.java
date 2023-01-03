package model;

import java.util.*;

public class RandomModelFactory {

    private static Random rd = new Random();

    /**
     * Requires: reach and size are positive integers
     * Ensures: the resulting GridModel is randomly initialized and solvable
     * */
    public static GridModel buildModel(int reach, int size){
        GridModel model = new GridModel(reach, initGrid(size));
        Set<Integer> visitedRows = new HashSet<>();
        Set<Integer> visitedColumns = new HashSet<>();
        for (int i = 0; i < Math.min(rd.nextInt(5) + 5, size / 2 + 1); i++){
            int x = rd.nextInt(size);
            int y = rd.nextInt(size);
            if (!(visitedColumns.contains(x) && visitedRows.contains(y))){
                model.toggleCell(x, y);
                visitedRows.add(y);
                visitedColumns.add(x);
            }
        }
        if (!model.lightsAreOut()){
            return model;
        } else {
            return buildModel(reach, size);
        }
    }

    private static List<List<Integer>> initGrid(int size){
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
