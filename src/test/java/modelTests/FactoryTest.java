package modelTests;

import model.GridModel;
import model.RandomModelFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FactoryTest {

    private Random rd = new Random();



    /*
    * Tests for max reach
    *
    * */
    @Test
    public void isSolvableOddRandomTests(){
        int size;
        for (int i = 0; i < 2000; i++){
            size = rd.nextInt(10) + 1;
            size = size % 2 == 0 ? size + 1 : size;
            GridModel m = RandomModelFactory.buildModel(size, size);
            List<List<Integer>> grid = m.getGrid();
            for (int j = 0; j < size; j++){
                Assertions.assertEquals(sumOfCol(grid, j) % 2, sumOfRow(grid, j) % 2);
            }
        }
    }

    @Test
    public void isNotSolved(){
        for (int i = 0; i < 2000; i++){
            boolean result = false;
            int size = rd.nextInt(10) + 1;
            GridModel m = RandomModelFactory.buildModel(size, size);
            List<List<Integer>> grid = m.getGrid();
            for (List<Integer> row : grid){
                Set<Integer> asSet = new HashSet<>(row);
                result = result || asSet.contains(1);
            }
            Assertions.assertTrue(result);
        }
    }

    private int sumOfRow(List<List<Integer>> grid, int row){
        int sum = 0;
        for (Integer i : grid.get(row)){
            sum += i;
        }
        return sum;
    }

    private int sumOfCol(List<List<Integer>> grid, int col){
        int sum = 0;
        for (List<Integer> row : grid){
            sum += row.get(col);
        }
        return sum;
    }


}
