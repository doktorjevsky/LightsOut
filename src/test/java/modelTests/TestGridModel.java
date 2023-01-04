package modelTests;

import model.staticHelpers.GenTestGrid;
import model.GridModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class TestGridModel {


    private GridModel grid2;

    @BeforeEach
    public void init(){
        grid2 = new GridModel(2, GenTestGrid.genGrid(2));
    }

    @Test
    public void lightsAreOut(){
        Assertions.assertTrue(grid2.lightsAreOut());
    }

    @Test
    public void lightsAreNotOut(){
        grid2.toggleCell(0, 0);
        Assertions.assertFalse(grid2.lightsAreOut());
    }

    // Mutating the grid in the GridModel doesn't mutate previously requested grids
    @Test
    public void testNotMutable1(){
        List<List<Integer>> before = grid2.getGrid();
        grid2.toggleCell(0, 0);
        List<List<Integer>> after = grid2.getGrid();

        boolean result = true;
        for (int i = 0; i < before.size(); i++){
            List<Integer> b = before.get(i);
            List<Integer> a = after.get(i);
            result = result && a.equals(b);
        }
        Assertions.assertFalse(result);
    }

    // Mutating a requested grid doesn't mutate the grid in the GridModel
    @Test
    public void testNotMutable2(){
        List<List<Integer>> before = grid2.getGrid();
        before.get(0).set(0, 100);
        List<List<Integer>> after = grid2.getGrid();

        boolean result = true;
        for (int i = 0; i < before.size(); i++){
            List<Integer> b = before.get(i);
            List<Integer> a = after.get(i);
            result = result && a.equals(b);
        }

        Assertions.assertFalse(result);
    }
}
