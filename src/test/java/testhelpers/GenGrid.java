package testhelpers;

import java.util.ArrayList;
import java.util.List;

public class GenGrid {

    public static List<List<Integer>> genGrid(int size){
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

