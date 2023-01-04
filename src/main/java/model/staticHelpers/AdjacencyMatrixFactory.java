package model.staticHelpers;

import model.GridModel;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrixFactory {


    public static List<List<Integer>> getAdjacencyMatrix(int size, int reach){
        GridModel dummy = new GridModel(reach, GenTestGrid.genGrid(size));
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

    private static List<Integer> getRow(List<List<Integer>> m){
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < m.size(); i++){
            for (int j = 0; j < m.get(0).size(); j++){
                row.add(m.get(i).get(j));
            }
        }
        return row;
    }


}
