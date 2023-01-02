package modelTests;

import model.GridModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
}
